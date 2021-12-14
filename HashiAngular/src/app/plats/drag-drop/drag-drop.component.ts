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
  constructor(
    private platService: PlatService,
    public sanitizer: DomSanitizer
  ) {}

  ngOnInit(): void {
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
