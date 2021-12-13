import { Component, OnInit } from '@angular/core';
@Component({
  selector: 'app-parametres-client',
  templateUrl: './parametres-client.component.html',
  styleUrls: ['./parametres-client.component.css'],
})
export class ParametresClientComponent implements OnInit {
  private _parametre: boolean = true;
  private _commandes: boolean = false;

  ngOnInit(): void {}

  get parametre(): boolean {
    return this._parametre;
  }
  get commandes(): boolean {
    return this._commandes;
  }

  getParams() {
    this._parametre = true;
    this._commandes = false;
  }

  getCommandes() {
    this._parametre = false;
    this._commandes = true;
  }
}
