import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LigneCarte } from 'src/app/model/ligne-carte';
import { ChoixRestaurantService } from 'src/app/services/choix-restaurant.service';

@Component({
  selector: 'app-panier',
  templateUrl: './panier.component.html',
  styleUrls: ['./panier.component.css']
})
export class PanierComponent implements OnInit {
  ligneCarte: LigneCarte[] = [];
  panier: Map<string, number> = new Map();
  panierEmpty : boolean = true;

  constructor(
    private choixRestaurantService: ChoixRestaurantService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.activatedRoute.params.subscribe((params) => {
      if (!!params["id"]) {
        this.choixRestaurantService
          .getPlatById(params["id"])
          .subscribe((result) => {
            this.ligneCarte = result;
          });
      }
    });
    this.panier = new Map(JSON.parse(localStorage.getItem('panier')!));
    if (this.panier.size > 0){
      this.panierEmpty = false;
    }
  }

  getQuantite(index: number): number {
    return this.panier.get(JSON.stringify(this.ligneCarte[index]))!;
  }

  addPanier(index: number) {
    if (this.panierEmpty){
      this.panierEmpty = false;
    }
    let ligneCarteEnString = JSON.stringify(this.ligneCarte[index]);
    let map: Map<string, number> = new Map(
      JSON.parse(localStorage.getItem('panier')!)
    );
    map.set(ligneCarteEnString, (map.get(ligneCarteEnString) ?? 0) + 1);
    this.panier = map;
    localStorage.setItem('panier', JSON.stringify([...this.panier]));
  }

  remove(ligneCarte: LigneCarte) {
    let ligneCarteEnString = JSON.stringify(ligneCarte);
    let map: Map<string, number> = new Map(
      JSON.parse(localStorage.getItem('panier')!)
    );
    if (map.get(ligneCarteEnString) !== 1) {
      map.set(ligneCarteEnString, (map.get(ligneCarteEnString) ?? 0) - 1);
    } else {
      map.delete(ligneCarteEnString);
    }
    this.panier = map;
    localStorage.setItem('panier', JSON.stringify([...this.panier]));
    if (map.size == 0){
      this.panierEmpty = true;
    }
  }
}
