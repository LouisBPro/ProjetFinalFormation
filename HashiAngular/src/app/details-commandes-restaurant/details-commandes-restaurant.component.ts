import { ActivatedRoute } from '@angular/router';
import { ChoixRestaurantService } from 'src/app/services/choix-restaurant.service';
import { DomSanitizer } from '@angular/platform-browser';
import { Commande } from './../model/commande';
import { Observable } from 'rxjs';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-details-commandes-restaurant',
  templateUrl: './details-commandes-restaurant.component.html',
  styleUrls: ['./details-commandes-restaurant.component.css'],
})
export class DetailsCommandesRestaurantComponent implements OnInit {
  commandes: Commande[] = [];
  commandeDetaillee: Commande = new Commande();
  afficherDetails: boolean = false;
  constructor(
    private restaurantService: ChoixRestaurantService,
    public sanitizer: DomSanitizer,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.params.subscribe((params) => {
      if (!!params['id']) {
        this.restaurantService
          .getCommandesByRestaurant(params['id'])
          .subscribe((o) => {
            this.commandes = o;
          });
      }
    });
  }

  getDetails(id: number) {
    this.restaurantService.getCommandesById(id).subscribe((o) => {
      this.commandeDetaillee = o;
      this.afficherDetails = true;
    });
  }
}
