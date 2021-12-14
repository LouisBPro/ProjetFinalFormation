import { ChoixRestaurantService } from "./../../services/choix-restaurant.service";
import { ActivatedRoute, Router } from "@angular/router";
import { Component, OnInit } from "@angular/core";
import { ValiderPanierService } from "src/app/services/valider-panier.service";
import { Restaurant } from "src/app/model/restaurant";

@Component({
  selector: "app-valider",
  templateUrl: "./valider.component.html",
  styleUrls: ["./valider.component.css"],
})
export class ValiderComponent implements OnInit {
  // On sépare le panier en autant de paniers qu'il y a de restaurants différents
  panierParRestaurant: Map<string, number>[] = [];
  // On tient une liste pour chaque nouveau restaurant avec son ID (pour aller les chercher uniquement à la fin)
  restaurantsIds: number[] = [];

  constructor(
    private validerService: ValiderPanierService,
    private activatedRoute: ActivatedRoute,
    private restaurantService: ChoixRestaurantService,
    private router: Router
  ) {}

  ngOnInit(): void {
    // SEPARATION EN PLUSIEURS PANIERS
    // On récupère le panier
    let panier: Map<string, number> = new Map(
      JSON.parse(localStorage.getItem("panier")!)
    );
    // On parcours tous les élements du panier
    panier.forEach((value: number, key: string) => {
      // Pour chaque ligne de commande on regarde si l'ID du restaurant est nouveau, si oui on l'ajoute
      let restaurantId: number = JSON.parse(key)["id"]["restaurant"]["id"];
      if (!this.restaurantsIds.includes(restaurantId)) {
        this.restaurantsIds.push(restaurantId);
        // On créé un nouveau panier pour le nouveau restaurant
        this.panierParRestaurant[this.restaurantsIds.length - 1] = new Map<
          string,
          number
        >();
      }
      // On ajoute chaque ligne de commande dans la map qui correspond à son restaurant
      if (this.restaurantsIds.length > 0) {
        // Comme chaque panier séparé est créé pour un seul restaurant alors on sait que c'est la map au même index que l'id du restaurant
        let index: number = this.restaurantsIds.indexOf(restaurantId);
        this.panierParRestaurant[index].set(key, value);
      }
    });

    let index = 0;
    this.panierParRestaurant.forEach((panier: Map<string, number>) => {
      // CALCUL DU TOTAL DE CHAQUE PANIER
      let prixTotal = 0;
      panier.forEach((value: number, key: string) => {
        prixTotal += JSON.parse(key)["id"]["plat"]["prix"] * value;
      });
      // ENREGISTREMENT DE CHAQUE PANIER
      this.restaurantService
        .getById(this.restaurantsIds[index])
        .subscribe((restaurant) => {
          this.validerService
            .enregistrer(restaurant, (prixTotal as unknown) as string, panier)
            .subscribe((ok) => {
              localStorage.removeItem("panier");
            });
        });
      index++;
    });
    this.goHome();
    console.log("gohome");
  }
  goHome() {
    this.router.navigate(["/home"]);
  }
}
