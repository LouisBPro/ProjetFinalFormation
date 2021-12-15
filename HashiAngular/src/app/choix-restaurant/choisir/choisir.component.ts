import { LigneCarte } from "./../../model/ligne-carte";
import { Restaurant } from "./../../model/restaurant";
import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { Observable } from "rxjs";
import { ChoixRestaurantService } from "src/app/services/choix-restaurant.service";

@Component({
  selector: "app-choisir",
  templateUrl: "./choisir.component.html",
  styleUrls: ["./choisir.component.css"],
})
export class ChoisirComponent implements OnInit {
  ligneCarte: LigneCarte[] | undefined;
  constructor(
    private choixRestaurantService: ChoixRestaurantService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.activatedRoute.params.subscribe((params) => {
      if (!!params["id"]) {
        this.choixRestaurantService
          .getPlatById(params["id"])
          .subscribe((result) => {
            this.ligneCarte = result;
          });
      }
    });
  }
}
