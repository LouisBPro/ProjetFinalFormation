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
// Je me prends une erreur 400 quand je veux insert mon restaurant.. => c'est parceque je dois faire un getById des cuistots que je prends.. (voir save)
// faut aussi gerer pour la carte
export class CreateRestaurantComponent implements OnInit {
  _form: FormGroup;
  cuisiniersAvailable: Observable<Cuisinier[]> | undefined = undefined;
  cuisiniersRecrutes : Cuisinier[] | undefined = [];

  constructor(
    private restaurantService: ChoixRestaurantService,
    private router: Router,
    private authService: AuthService,
    private cuisinierService: CuisinierService
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
      cuisiniers: new FormControl(''),
    });
  }
  ngOnInit(): void {
    this.cuisiniersAvailable = this.cuisinierService.allCuisinierAvailable();
  }

  get form(): { [key: string]: AbstractControl } {
    return this._form.controls;
  }

  save() {
    if (this.form['cuisiniers'].value?.length > 0){
      this.form['cuisiniers'].value?.forEach( (id: number) => {
          this.cuisinierService.byId(id).subscribe((ok) => {
            this.cuisiniersRecrutes?.push(ok);
            if (this.cuisiniersRecrutes?.length == this.form['cuisiniers'].value.length){
              this.insert();
            }
          })
      });
    } else{
      this.cuisiniersRecrutes=[];
      this.insert();
    }
  }

  insert(){
    this.restaurantService.insert(
      new Restaurant(
        new Adresse(
          this.form['numero'].value,
          this.form['rue'].value,
          this.form['codePostal'].value,
          this.form['ville'].value
        ),
        undefined,
        undefined,
        this.form['nom'].value,
        undefined,
        this.cuisiniersRecrutes
      )
    ).subscribe((restaurant) => {
      this.router.navigate(['/home']);
    }, (error) => {
      console.log("Erreur création de restaurant");
    });
  }
}
