import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { Observable } from "rxjs";
import { Restaurant } from "src/app/model/restaurant";
import { ChoixRestaurantService } from "src/app/services/choix-restaurant.service";

@Component({
  selector: "app-choisir",
  templateUrl: "./choisir.component.html",
  styleUrls: ["./choisir.component.css"],
})
export class ChoisirComponent implements OnInit {
  restaurants: Observable<String> | undefined = undefined;
  constructor(
    private choixRestaurantService: ChoixRestaurantService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    // this.activatedRoute.params.subscribe((params) => {
    //   if (!!params["id"]) {
    //     this.choixRestaurantService.getPlatById(params["id"]).subscribe((result) => {
    //       this.restaurants = result;
    //       console.log(result);
    //     });
    //   }
  }
}
