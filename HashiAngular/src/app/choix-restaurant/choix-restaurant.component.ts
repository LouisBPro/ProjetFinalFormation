import { ChoixRestaurantService } from "./../services/choix-restaurant.service";
import { Restaurant } from "./../model/restaurant";
import { Component, OnInit } from "@angular/core";
import { Observable } from "rxjs";

@Component({
  selector: "app-choix-restaurant",
  templateUrl: "./choix-restaurant.component.html",
  styleUrls: ["./choix-restaurant.component.css"],
})
export class ChoixRestaurantComponent implements OnInit {
  restaurants: Observable<Restaurant[]> | undefined = undefined;
  constructor(private choixRestaurantService: ChoixRestaurantService) {}

  ngOnInit(): void {
    this.restaurants = this.choixRestaurantService.allRestaurant();
  }
}
