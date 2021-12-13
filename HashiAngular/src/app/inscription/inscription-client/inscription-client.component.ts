import { AuthService } from '../../services/auth.service';
import { Observable } from 'rxjs';
import {
  AbstractControl,
  ValidationErrors,
  AsyncValidatorFn,
} from '@angular/forms';
import { FormGroup, FormControl, Validators } from '@angular/forms';
// import { Civilite } from './../../model/civilite';
import { Component, OnInit } from '@angular/core';
import { debounceTime, map } from 'rxjs/operators';
import { Client } from 'src/app/model/client';
import { ClientService } from '../../services/client.service';
import { Router } from '@angular/router';
import { User } from '../../model/user';
import { Adresse } from '../../model/adresse';

@Component({
  selector: 'app-inscription-client',
  templateUrl: './inscription-client.component.html',
  styleUrls: ['./inscription-client.component.css'],
})
export class InscriptionClientComponent {
  _form: FormGroup;

  constructor(
    private clientService: ClientService,
    private router: Router,
    private authService: AuthService
  ) {
    this._form = new FormGroup({
      prenom: new FormControl('', [
        Validators.required,
        Validators.pattern(/^[a-zA-ZÀ-ÿ]{1,}((\s|-)[a-zA-ZÀ-ÿ]{1,})*$/),
        Validators.maxLength(50),
      ]),
      nom: new FormControl('', [
        Validators.required,
        Validators.pattern(/^[a-zA-ZÀ-ÿ]{1,}((\s|-)[a-zA-ZÀ-ÿ]{1,})*$/),
        Validators.maxLength(50),
      ]),
      email: new FormControl('', [
        Validators.required,
        Validators.email,
        Validators.maxLength(100),
      ]),
      numero: new FormControl('', [
        Validators.required,
        Validators.pattern(/^[a-zA-Z0-9À-ÿ]{1,}((\s|-)[a-zA-Z0-9À-ÿ]{1,})*$/),
        Validators.maxLength(50),
      ]),
      rue: new FormControl('', [
        Validators.required,
        Validators.pattern(/^[a-zA-Z0-9À-ÿ]{1,}((\s|-)[a-zA-Z0-9À-ÿ]{1,})*$/),
        Validators.maxLength(200),
      ]),
      codePostal: new FormControl('', [
        Validators.required,
        Validators.pattern(/^[a-zA-Z0-9À-ÿ]{1,}((\s|-)[a-zA-Z0-9À-ÿ]{1,})*$/),
        Validators.maxLength(50),
      ]),
      ville: new FormControl('', [
        Validators.required,
        Validators.pattern(/^[a-zA-ZÀ-ÿ]{1,}((\s|-)[a-zA-ZÀ-ÿ]{1,})*$/),
        Validators.maxLength(100),
      ]),
      login: new FormControl(
        '',
        [
          Validators.required,
          Validators.pattern(/^[a-zA-ZÀ-ÿ0-9]{5,}$/),
          Validators.maxLength(20),
        ],
        this.checkLogin()
      ),
      passwordGroup: new FormGroup(
        {
          password: new FormControl('', [
            Validators.required,
            Validators.pattern(/^[a-zA-ZÀ-ÿ0-9]{5,}$/),
            Validators.maxLength(30),
          ]),
          confirm: new FormControl(''),
        },
        this.checkNotEquals
      ),
    });
  }

  get form(): { [key: string]: AbstractControl } {
    return this._form.controls;
  }

  checkLogin(): AsyncValidatorFn {
    return (control: AbstractControl): Observable<ValidationErrors | null> => {
      return this.clientService.checkLogin(control.value).pipe(
        debounceTime(1000),
        map((res: boolean) => {
          return res ? { loginUsed: true } : null;
        })
      );
    };
  }

  checkNotEquals(group: AbstractControl): ValidationErrors | null {
    let formGroup: FormGroup = group as FormGroup;
    if (formGroup.controls['password'].errors) {
      return null;
    }
    return formGroup.controls['password'].value !=
      formGroup.controls['confirm'].value
      ? { checkNotEquals: true }
      : null;
  }

  save() {
    this.clientService
      .insert(
        new Client(
          undefined,
          this.form['prenom'].value,
          this.form['nom'].value,
          this.form['email'].value,
          new User(this.form['login'].value),
          new Adresse(
            this.form['numero'].value,
            this.form['rue'].value,
            this.form['codePostal'].value,
            this.form['ville'].value
          )
        ),
        this.form['passwordGroup'].get('password')!.value
      )
      .subscribe(
        (client) => {
          this.authService
            .auth(
              this.form['login'].value,
              this.form['passwordGroup'].get('password')!.value
            )
            .subscribe((ok) => {
              sessionStorage.setItem(
                'token',
                btoa(
                  this.form['login'].value +
                    ':' +
                    this.form['passwordGroup'].get('password')!.value
                )
              );
              sessionStorage.setItem('login', this.form['login'].value);
              sessionStorage.setItem('id', ok['personne']['id']);
              switch (ok['roles'][0]) {
                case 'ROLE_CLIENT':
                  sessionStorage.setItem('role', 'client');
                  break;
                case 'ROLE_CUISINIER':
                  sessionStorage.setItem('role', 'cuisinier');
                  break;
                case 'ROLE_GERANT':
                  sessionStorage.setItem('role', 'gerant');
                  break;
                case 'ROLE_ADMIN':
                  sessionStorage.setItem('role', 'admin');
                  break;
                default:
                  sessionStorage.setItem('role', 'none');
                  break;
              }
              this.router.navigate(['/home']);
            });
        },
        (error) => {
          console.log('erreur création compte client');
        }
      );
  }
}
