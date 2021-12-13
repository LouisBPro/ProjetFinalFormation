import { ChoixRestaurantService } from './../../services/choix-restaurant.service';
import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { ValiderPanierService } from 'src/app/services/valider-panier.service';

@Component({
  selector: 'app-valider',
  templateUrl: './valider.component.html',
  styleUrls: ['./valider.component.css']
})
export class ValiderComponent implements OnInit {
  constructor(private validerService: ValiderPanierService, private activatedRoute : ActivatedRoute, private restaurantService : ChoixRestaurantService) {}

  ngOnInit(): void {
    this.activatedRoute.params.subscribe((params) => {
      if (!!params["id"]) {
        this.restaurantService
          .getById(params["id"])
          .subscribe((restaurant) => {
            this.validerService.enregistrer(restaurant,localStorage.getItem('totalPanier')).subscribe((ok) => {
              localStorage.removeItem('panier');
              localStorage.removeItem('panierTotal');
            });
          });
      }
    });

  }
}
