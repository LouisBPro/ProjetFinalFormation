import { CuisinierService } from './../services/cuisinier.service';
import { AuthService } from '../services/auth.service';
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
import { Restaurant } from 'src/app/model/restaurant';
import { Router } from '@angular/router';
import { User } from '../model/user';
import { Adresse } from '../model/adresse';
import { ChoixRestaurantService } from '../services/choix-restaurant.service';
import { Cuisinier } from '../model/cuisinier';

@Component({
  selector: 'app-create-restaurant',
  templateUrl: './create-restaurant.component.html',
  styleUrls: ['./create-restaurant.component.css'],
})
// Ici j'ai pas fini :
// faut tester si ça affiche bien les cuisiniers qui sont disponibles car j'ai pas essayé ma requete
// faut pouvoir choisir plusieurs cuisiniers et mettre cette liste dans le create
// faut aussi gerer pour la carte
export class CreateRestaurantComponent implements OnInit{
  _form: FormGroup;
  cuisiniers: Observable<Cuisinier[]> | undefined = undefined;

  constructor(
    private restaurantService: ChoixRestaurantService,
    private router: Router,
    private authService: AuthService,
    private cuisinierService : CuisinierService
  ) {
    this._form = new FormGroup({
      nom: new FormControl('', [
        Validators.required,
        Validators.pattern(/^[a-zA-ZÀ-ÿ]{1,}((\s|-)[a-zA-ZÀ-ÿ]{1,})*$/),
        Validators.maxLength(50),
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
      cuisinier: new FormControl('')
    });
  }
  ngOnInit(): void {
    this.cuisiniers = this.cuisinierService.allCuisinierAvailable();
  }

  get form(): { [key: string]: AbstractControl } {
    return this._form.controls;
  }

  save() {
    this.restaurantService
      .insert(
        new Restaurant(
          new Adresse(
            this.form['numero'].value,
            this.form['rue'].value,
            this.form['codePostal'].value,
            this.form['ville'].value
          ),
          this.form['nom'].value,
          this.form['email'].value
        ),
        this.form['passwordGroup'].get('password')!.value
      )
      .subscribe(
        (restaurant) => {
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
              if (!!ok['restaurant']) {
                sessionStorage.setItem('role', 'restaurant');
              } else {
                sessionStorage.setItem('role', 'admin');
              }
              this.router.navigate(['/home']);
            });
        },
        (error) => {
          console.log('erreur création compte restaurant');
        }
      );
  }
}
