import { PlatService } from "./../services/plat-service";
import { Component, OnInit } from "@angular/core";
import { Observable } from "rxjs";
import { Plat } from "../model/plat";

@Component({
  selector: "app-plats",
  templateUrl: "./plats.component.html",
  styleUrls: ["./plats.component.css"],
})
export class PlatsComponent implements OnInit {
  plats: Observable<Plat[]> | undefined = undefined;
  constructor(private platService: PlatService) {}

  ngOnInit(): void {
    this.plats = this.platService.allPlats();
  }
  delete(id: number | undefined) {
    if (!!id) {
      this.platService.delete(id).subscribe((result) => {
        this.plats = this.platService.allPlats();
      });
    }
  }
}
