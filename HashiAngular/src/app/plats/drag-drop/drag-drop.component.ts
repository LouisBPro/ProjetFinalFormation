import { LigneCartePk } from './../../model/ligne-carte-pk';
import { Restaurant } from 'src/app/model/restaurant';
import { LigneCarte } from './../../model/ligne-carte';

import { ChoixRestaurantService } from 'src/app/services/choix-restaurant.service';
import { ActivatedRoute, Router } from '@angular/router';

import { Component, OnInit } from '@angular/core';
import {
  CdkDragDrop,
  moveItemInArray,
  transferArrayItem,
} from '@angular/cdk/drag-drop';
import { PlatService } from 'src/app/services/plat.service';
import { Observable } from 'rxjs';
import { Plat } from 'src/app/model/plat';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-drag-drop',
  templateUrl: './drag-drop.component.html',
  styleUrls: ['./drag-drop.component.css'],
})
export class DragDropComponent implements OnInit {
  plats: Plat[] = [];
  listrestau: Plat[] = [];
  ligneCarte: LigneCarte[] = [];
  RestaurantId: number = 0;
  restaurant: Restaurant = new Restaurant();

  lc: LigneCarte = new LigneCarte();
  lcPk: LigneCartePk = new LigneCartePk();

  constructor(
    private choixRestaurantService: ChoixRestaurantService,
    private platService: PlatService,
    public sanitizer: DomSanitizer,
    private activatedRoute: ActivatedRoute,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.activatedRoute.params.subscribe((params) => {
      if (!!params['id']) {
        this.RestaurantId = params['id'];
        this.choixRestaurantService
          .getById(params['id'])
          .subscribe((restaurant) => {
            this.restaurant = restaurant;
            restaurant.lignesCarte?.forEach((lc) => {
              this.ligneCarte.push(lc);
              this.listrestau.push(lc.id?.plat!);
            });
          });
      }
    });
    this.platService.allPlats().subscribe((plat) => {
      this.plats = plat;
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
    if (this.listrestau.length == 0){
      this.choixRestaurantService.resetCarteRestaurant(this.restaurant).subscribe((ok)=>{
        console.log("Carte du restaurant vidée");
        this.router.navigate(['/home']);
      }, (error)=>{
        console.log("impossible de vider la carte du restaurant");
      });
    }
    else{
      let platsAjoutes = 0;
      this.choixRestaurantService.resetCarteRestaurant(this.restaurant).subscribe((ok)=>{
        this.ligneCarte = [];
        this.listrestau.forEach((plat) => {
          this.lcPk = new LigneCartePk();
          this.lc = new LigneCarte();
          this.lcPk.plat = plat;
          this.lcPk.restaurant = undefined;
          this.lc.id = this.lcPk;
          this.lc.disponibilite = true;
          this.ligneCarte.push(this.lc);
        });
        this.restaurant.lignesCarte = this.ligneCarte;
        this.restaurant.lignesCarte.forEach((lc)=>{
          this.choixRestaurantService.updateCarteRestaurant(this.restaurant, lc.id?.plat!.id!).subscribe(
            (log) => {
              console.log('Plat ' + lc.id?.plat?.nom + 'ajouté');
              platsAjoutes++;
              if (platsAjoutes >= this.listrestau.length){
                // Tout a été ajouté avec succès
                this.router.navigate(['/home']);
              }
            },
            (error) => {
              console.log("Impossible d'ajouter le plat "+ lc.id?.plat?.nom);
            }
          );
        });
      }, (error)=>{
        console.log("Impossible de reset la carte du restaurant")
      })
    }
  }
}
