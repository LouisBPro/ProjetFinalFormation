import { LigneCartePk } from "./../../model/ligne-carte-pk";
import { Restaurant } from "src/app/model/restaurant";
import { LigneCarte } from "./../../model/ligne-carte";

import { ChoixRestaurantService } from "src/app/services/choix-restaurant.service";
import { ActivatedRoute } from "@angular/router";

import { Component, OnInit } from "@angular/core";
import {
  CdkDragDrop,
  moveItemInArray,
  transferArrayItem,
} from "@angular/cdk/drag-drop";
import { PlatService } from "src/app/services/plat.service";
import { Observable } from "rxjs";
import { Plat } from "src/app/model/plat";
import { DomSanitizer } from "@angular/platform-browser";

@Component({
  selector: "app-drag-drop",
  templateUrl: "./drag-drop.component.html",
  styleUrls: ["./drag-drop.component.css"],
})
export class DragDropComponent implements OnInit {
  plats: Plat[] = [];
  listrestau: Plat[] = [];
  ligneCarte: LigneCarte[] = [];
  RestaurantId: number = 0;
  constructor(
    private choixRestaurantService: ChoixRestaurantService,
    private platService: PlatService,
    public sanitizer: DomSanitizer,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.params.subscribe((params) => {
      if (!!params["id"]) {
        this.RestaurantId = params["id"];
        this.choixRestaurantService
          .getPlatById(params["id"])
          .subscribe((result) => {
            this.ligneCarte = result;
            for (let lc of this.ligneCarte) {
              console.log(lc!.id!.plat!);
              this.listrestau.push(lc!.id!.plat!);
            }
            console.log(result);
          });
      }
    });
    this.platService.allPlats().subscribe((client) => {
      this.plats = client;
      this.unique();
    });
  }
  unique() {
    let index1 = [];
    let index2 = [];
    for (const item of this.plats) {
      index1.push(item.id);
    }
    for (const item of this.listrestau) {
      index2.push(item.id);
    }
    for (const i of index2) {
      console.log("i", i);
      console.log(index1.indexOf(i));
      if (index1.indexOf(i) != -1) {
        this.plats.splice(index1.indexOf(i), 1);
      }
      index1.splice(index1.indexOf(i), 1);
    }
  }
  drop(event: CdkDragDrop<Plat[]>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(
        event.container.data,
        event.previousIndex,
        event.currentIndex
      );
    } else {
      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex
      );
    }
  }
  save() {
    console.log(this.listrestau);

    this.choixRestaurantService
      .getById(this.RestaurantId)
      .subscribe((Resto) => {
        let carte = new LigneCarte();
        let cartepk = new LigneCartePk();
        let resto = new Restaurant();
        resto = Resto;
        for (const plat of this.listrestau) {
          cartepk.plat = plat;

          carte.disponibilite = true;
          carte.id = cartepk;

          this.ligneCarte.push(carte);
        }
        resto.ligneCarte = this.ligneCarte;
        this.choixRestaurantService.updateCarteRestaurant(resto).subscribe(
          (log) => {
            console.log("ok");
          },
          (error) => {
            console.log(error);
          }
        );
        console.log("carte resto modif");
      });
  }
}
