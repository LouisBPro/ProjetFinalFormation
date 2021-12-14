import { DomSanitizer } from '@angular/platform-browser';
import { ChoixRestaurantService } from 'src/app/services/choix-restaurant.service';
import { Restaurant } from 'src/app/model/restaurant';
import { Observable } from 'rxjs';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-commandes-restaurant',
  templateUrl: './commandes-restaurant.component.html',
  styleUrls: ['./commandes-restaurant.component.css'],
})
export class CommandesRestaurantComponent implements OnInit {
  restaurants: Observable<Restaurant[]> | undefined = undefined;
  constructor(
    private choixRestaurantService: ChoixRestaurantService,
    public sanitizer: DomSanitizer
  ) {}

  ngOnInit(): void {
    this.restaurants = this.choixRestaurantService.allRestaurant();
  }
}
