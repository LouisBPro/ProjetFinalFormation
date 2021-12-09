import { Plat } from "./../../model/plat";
import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { PlatService } from "src/app/services/plat.service";

@Component({
  selector: "app-edit-plat",
  templateUrl: "./edit-plat.component.html",
  styleUrls: ["./edit-plat.component.css"],
})
export class EditPlatComponent implements OnInit {
  plat: Plat = new Plat();
  constructor(
    private activatedRoute: ActivatedRoute,
    private platService: PlatService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.activatedRoute.params.subscribe((params) => {
      if (!!params["id"]) {
        this.platService.getById(params["id"]).subscribe((result) => {
          this.plat = result;
          console.log(result);
        });
      }
    });
  }
  save() {
    if (!!this.plat.id) {
      this.platService.update(this.plat).subscribe((result) => {
        this.goList();
      });
    } else {
      this.platService.insert(this.plat).subscribe((result) => {
        this.goList();
      });
    }
  }

  goList() {
    this.router.navigate(["/plats"]);
  }
}
