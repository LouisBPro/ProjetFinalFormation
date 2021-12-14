import { LigneCarte } from "src/app/model/ligne-carte";
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
  constructor(
    private choixRestaurantService: ChoixRestaurantService,
    private platService: PlatService,
    public sanitizer: DomSanitizer,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.params.subscribe((params) => {
      if (!!params["id"]) {
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
    });
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
}
