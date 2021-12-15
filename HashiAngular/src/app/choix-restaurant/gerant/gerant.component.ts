import { ChoixRestaurantService } from "src/app/services/choix-restaurant.service";
import { Component, OnInit } from "@angular/core";
import { Observable } from "rxjs";
import { Restaurant } from "src/app/model/restaurant";
import { Select } from "@material-ui/core";
import { FormBuilder, FormControl, FormGroup } from "@angular/forms";
import { Router } from "@angular/router";

@Component({
  selector: "app-gerant",
  templateUrl: "./gerant.component.html",
  styleUrls: ["./gerant.component.css"],
})
export class GerantComponent implements OnInit {
  form: FormGroup;
  value: any;
  selectControl = new FormControl("1");

  restaurants: Restaurant[] = [];
  constructor(
    private choixRestaurantService: ChoixRestaurantService,
    private router: Router,
    private formBuilder: FormBuilder
  ) {
    this.form = this.formBuilder.group({
      selectControl: [""],
    });
  }

  ngOnInit(): void {
    this.choixRestaurantService.allRestaurant().subscribe((resto) => {
      this.restaurants = resto;
      console.log(this.restaurants);
    });
  }
  submit() {
    const stingifyvalue = JSON.stringify(this.form.value).split('":"');
    this.value = stingifyvalue[1].replace('"}', "");
    console.log("value is =>", this.value);
    this.router.navigate(["/gerant/drag/" + this.value]);
  }
  redirect(value: any) {
    this.router.navigate(["/gerant/drag/" + value]);
  }
}
