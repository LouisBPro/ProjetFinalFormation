import { AppComponent } from "./../app.component";
import { Component, OnInit } from "@angular/core";
import { DomSanitizer } from "@angular/platform-browser";
import { Observable } from "rxjs";
import { Plat } from "../model/plat";
import { PlatService } from "../services/plat.service";

@Component({
  selector: "app-home",
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.css"],
})
export class HomeComponent implements OnInit {
  plats: Plat[] = [];

  constructor(
    private platService: PlatService,
    public sanitizer: DomSanitizer
  ) {}

  ngOnInit(): void {}
  get roleGerant(): Boolean {
    const role = sessionStorage.getItem("role");
    if (role == "gerant") {
      return true;
    } else {
      return false;
    }
  }
}
