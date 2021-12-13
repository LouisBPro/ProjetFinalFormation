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
import { Cuisinier } from 'src/app/model/cuisinier';
import { CuisinierService } from '../../services/cuisinier.service';
import { Router } from '@angular/router';
import { User } from '../../model/user';

@Component({
  selector: 'app-inscription-cuisinier',
  templateUrl: './inscription-cuisinier.component.html',
  styleUrls: ['./inscription-cuisinier.component.css'],
})
export class InscriptionCuisinierComponent implements OnInit{
  _form: FormGroup;
  restaurants: Observable<Restaurant[]> | undefined = undefined;

  constructor(
    private cuisinierService: CuisinierService,
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
      restaurant : new FormControl('', [
        Validators.required,
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

  ngOnInit(): void {
    this.restaurants = this.restaurantService.allRestaurant();
  }

  get form(): { [key: string]: AbstractControl } {
    return this._form.controls;
  }

  checkLogin(): AsyncValidatorFn {
    return (control: AbstractControl): Observable<ValidationErrors | null> => {
      return this.cuisinierService.checkLogin(control.value).pipe(
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
    this.restaurantService.getById(this.form['restaurant'].value).subscribe((restau) =>{
      this.cuisinierService
        .insert(
          new Cuisinier(
            new User(this.form['login'].value),
            undefined,
            this.form['prenom'].value,
            this.form['nom'].value,
            this.form['email'].value,
            restau
          ),
          this.form['passwordGroup'].get('password')!.value
        )
        .subscribe(
          (cuisinier) => {
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
            console.log('erreur création compte cuisinier');
          }
        );
    }, (erreur) =>{
      console.log('restaurant non trouvé dans la BDD');
    }
    )
  }
}
