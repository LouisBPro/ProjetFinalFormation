import { GerantService } from './../services/gerant.service';
  import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Adresse } from '../model/adresse';
import { Restaurant } from '../model/restaurant';
import { AuthService } from '../services/auth.service';
import { ChoixRestaurantService } from '../services/choix-restaurant.service';

@Component({
  selector: 'app-get-available-restaurant-gerant',
  templateUrl: './get-available-restaurant-gerant.component.html',
  styleUrls: ['./get-available-restaurant-gerant.component.css']
})
export class GetAvailableRestaurantGerantComponent implements OnInit {

  _form: FormGroup;
  restaurantsAvailable: Observable<Restaurant[]> | undefined = undefined;
  restaurantsChoisis : Restaurant[] | undefined = [];

  constructor(
    private restaurantService: ChoixRestaurantService,
    private router: Router,
    private authService: AuthService,
    private gerantService : GerantService
  ) {
    this._form = new FormGroup({
      restaurantsDispo: new FormControl(''),
    });
  }

  ngOnInit(): void {
    this.restaurantsAvailable = this.restaurantService.allRestaurantAvailable();
  }

  get form(): { [key: string]: AbstractControl } {
    return this._form.controls;
  }

  save() {
    if (this.form['restaurantsDispo'].value?.length > 0){
      this.form['restaurantsDispo'].value?.forEach( (id: number) => {
          this.restaurantService.getById(id).subscribe((restaurant) => {
            this.restaurantsChoisis?.push(restaurant);
            if (this.restaurantsChoisis?.length == this.form['restaurantsDispo'].value.length){
              this.update();
            }
          })
      });
    } else{
      console.log("Aucun restaurant sélectionné");
      this.router.navigate(['/home']);
    }
  }

  // Alors là le pb c'est que je cherche à savoir comment donner des restaurants à mon gérant alors que c'est un .JSON => Voir son console.log
  update(){
    let restaurantsLeftsToManage = 0;
    this.gerantService.local().subscribe((gerantLocal)=>{
      this.restaurantsChoisis?.forEach((restaurant)=>{
        this.gerantService.manageNewRestaurantLocalGerant(restaurant.id!).subscribe((ok)=>{
          console.log("Nouveau restaurant managé: " + restaurant.nom);
          restaurantsLeftsToManage++;
          if (restaurantsLeftsToManage >= this.restaurantsChoisis?.length!){
            this.router.navigate(['/home']);
          }
        }, (error)=>{
          console.log("Erreur pour prendre le management du restaurant : " + restaurant.nom);
        })
      });
    })
  }

}
