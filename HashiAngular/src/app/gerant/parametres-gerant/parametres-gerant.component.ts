import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-parametres-gerant',
  templateUrl: './parametres-gerant.component.html',
  styleUrls: ['./parametres-gerant.component.css'],
})
export class ParametresGerantComponent implements OnInit {
  private _parametre: boolean = true;
  private _restaurants: boolean = false;

  ngOnInit(): void {}

  get parametre(): boolean {
    return this._parametre;
  }
  get restaurants(): boolean {
    return this._restaurants;
  }

  getParams() {
    this._parametre = true;
    this._restaurants = false;
  }

  getRestaurants() {
    this._parametre = false;
    this._restaurants = true;
  }
}
