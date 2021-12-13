import { Plat } from "./../../model/plat";
import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { PlatService } from "src/app/services/plat.service";
import { HttpClient } from "@angular/common/http";

@Component({
  selector: "app-edit-plat",
  templateUrl: "./edit-plat.component.html",
  styleUrls: ["./edit-plat.component.css"],
})
export class EditPlatComponent implements OnInit {
  plat: Plat = new Plat();
  fileName = "";
  title = "ImageUploaderFrontEnd";
  public selectedFile: any;
  public event1: any;
  imgURL: any;
  receivedImageData: any;
  base64Data: any;
  convertedImage: any;

  constructor(
    private activatedRoute: ActivatedRoute,
    private platService: PlatService,
    private router: Router,
    private http: HttpClient
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
  public onFileChanged(event: any) {
    console.log(event);
    this.selectedFile = event.target.files[0];

    // Below part is used to display the selected image
    let reader = new FileReader();
    reader.readAsDataURL(event.target.files[0]);
    reader.onload = (event2) => {
      this.imgURL = reader.result;
    };
  }
  onUpload() {
    this.platService.Upload(this.plat, this.selectedFile).subscribe(
      (res) => {
        console.log(res);
        this.receivedImageData = res;
        // this.base64Data = this.receivedImageData.pic;
        // this.convertedImage = "data:image/jpeg;base64," + this.base64Data;
      },
      (err) => console.log("Error Occured duringng saving: " + err)
    );
  }
}
