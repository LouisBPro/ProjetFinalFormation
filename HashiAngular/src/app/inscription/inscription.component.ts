import { AuthService } from './../services/auth.service';
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
import { ClientService } from '../services/client.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.css'],
})
export class InscriptionComponent implements OnInit {
  _form: FormGroup;

  constructor(private clientService: ClientService, private router: Router, private authService : AuthService) {
    this._form = new FormGroup({
      prenom: new FormControl('', [
        Validators.required,
        // Validators.pattern(/^[a-zA-Z]{1,}((\s|-)[a-zA-Z]{2,})*$/),
        Validators.maxLength(200),
      ]),
      nom: new FormControl('', [
        Validators.required,
        // Validators.pattern(/^[a-zA-Z]{1,}((\s|-)[a-zA-Z]{2,})*$/),
        Validators.maxLength(200),
      ]),
      email: new FormControl('', [
        Validators.required,
        // Validators.pattern(/^[a-zA-Z]{1,}((\s|-)[a-zA-Z]{2,})*$/),
        Validators.email,
        Validators.maxLength(200),
      ]),
      numero: new FormControl('', [
        Validators.required,
        // Validators.pattern(/^[a-zA-Z]{1,}((\s|-)[a-zA-Z]{2,})*$/),
        // Validators.maxLength(200),
      ]),
      rue: new FormControl('', [
        Validators.required,
        // Validators.pattern(/^[a-zA-Z]{1,}((\s|-)[a-zA-Z]{2,})*$/),
        Validators.maxLength(200),
      ]),
      codePostal: new FormControl('', [
        Validators.required,
        // Validators.pattern(/^[a-zA-Z]{1,}((\s|-)[a-zA-Z]{2,})*$/),
        // Validators.maxLength(200),
      ]),
      ville: new FormControl('', [
        Validators.required,
        // Validators.pattern(/^[a-zA-Z]{1,}((\s|-)[a-zA-Z]{2,})*$/),
        Validators.maxLength(200),
      ]),
      login: new FormControl('', Validators.required, this.checkLogin()),
      passwordGroup: new FormGroup(
        {
          password: new FormControl('', [
            Validators.required,
            // Validators.pattern(
              // /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])([a-zA-Z0-9$@#_-]{5,25})$/
            // ),
          ]),
          confirm: new FormControl(''),
        },
        this.checkNotEquals
      ),
    });
  }

  get form(): { [key: string]: AbstractControl; }
  {
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
    return formGroup.controls['password'].value != formGroup.controls['confirm'].value
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
          this.form['login'].value,
          this.form['numero'].value,
          this.form['rue'].value,
          this.form['codePostal'].value,
          this.form['ville'].value
        ),
        this.form['passwordGroup'].get('password')!.value
      )
      .subscribe((client) => {
        this.authService.auth(this.form['login'].value, this.form['passwordGroup'].get('password')!.value).subscribe(
          (ok) => {
            sessionStorage.setItem('token', btoa(this.form['login'].value + ':' + this.form['passwordGroup'].get('password')!.value));
            sessionStorage.setItem('login', this.form['login'].value);
            // TODO roles
            if (!!ok['client']) {
              sessionStorage.setItem('role', 'client');
            } else {
              sessionStorage.setItem('role', 'admin');
            }
            if (!!localStorage.getItem('valider')) {
              localStorage.removeItem('valider');
              this.router.navigate(['/valider']);
            } else {
              this.router.navigate(['/home']);
            }
          }
        );
      },
      (error) => {
        console.log("erreur création compte client");
      });
  }

  ngOnInit(): void {}

  nomErrorMessage() {
    if (this.form['nom']!.hasError('required')) {
      return 'nom obligatoire';
    } else if (this.form['nom']!.hasError('pattern')) {
      return 'uniquement des lettres avec espace ou -';
    }
    return 'maximum 200 caracteres';
  }
}
