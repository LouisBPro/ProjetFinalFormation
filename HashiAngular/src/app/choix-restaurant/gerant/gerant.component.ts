import { ChoixRestaurantService } from "src/app/services/choix-restaurant.service";
import { Component, OnInit } from "@angular/core";
import { Observable } from "rxjs";
import { Restaurant } from "src/app/model/restaurant";
import { Select } from "@material-ui/core";
import { FormControl, FormGroup } from "@angular/forms";
import { Router } from "@angular/router";

@Component({
  selector: "app-gerant",
  templateUrl: "./gerant.component.html",
  styleUrls: ["./gerant.component.css"],
})
export class GerantComponent implements OnInit {
  selectControl = new FormControl("1");
  restaurants: Restaurant[] = [];
  constructor(
    private choixRestaurantService: ChoixRestaurantService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.choixRestaurantService.allRestaurant().subscribe((resto) => {
      this.restaurants = resto;
      console.log(this.restaurants);
    });
    this.selectControl.valueChanges.subscribe((value: any) => {
      if (value != "null") {
        this.redirect(value);
      }
      console.log("Selected value:", value);
    });
  }
  redirect(value: any) {
    this.router.navigate(["/gerant/drag/" + value]);
  }
}
