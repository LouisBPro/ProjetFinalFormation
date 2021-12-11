import { ChoixRestaurantService } from './../../services/choix-restaurant.service';
import { Restaurant } from './../../model/restaurant';
import { AuthService } from '../../services/auth.service';
import { Observable } from 'rxjs';
import {
  AbstractControl,
  ValidationErrors,
  AsyncValidatorFn,
} from '@angular/forms';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { debounceTime, map } from 'rxjs/operators';
import { Gerant } from 'src/app/model/gerant';
import { GerantService } from '../../services/gerant.service';
import { Router } from '@angular/router';
import { User } from '../../model/user';

@Component({
  selector: 'app-inscription-gerant',
  templateUrl: './inscription-gerant.component.html',
  styleUrls: ['./inscription-gerant.component.css'],
})
export class InscriptionGerantComponent{
  _form: FormGroup;

  constructor(
    private gerantService: GerantService,
    private router: Router,
    private authService: AuthService,
    private restaurantService : ChoixRestaurantService
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
            Validators.pattern(
              /^[a-zA-ZÀ-ÿ0-9]{5,}$/
            ),
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
      return this.gerantService.checkLogin(control.value).pipe(
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
      this.gerantService
        .insert(
          new Gerant(
            new User(this.form['login'].value),
            undefined,
            this.form['prenom'].value,
            this.form['nom'].value,
            this.form['email'].value,
          ),
          this.form['passwordGroup'].get('password')!.value
        )
        .subscribe(
          (gerant) => {
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
                // TODO roles
                if (!!ok['gerant']) {
                  sessionStorage.setItem('role', 'gerant');
                } else {
                  sessionStorage.setItem('role', 'admin');
                }
                this.router.navigate(['/home']);
              });
          },
          (error) => {
            console.log('erreur création compte gerant');
          }
        );
  }
}
