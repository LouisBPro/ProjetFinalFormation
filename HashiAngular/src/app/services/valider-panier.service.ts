import { ChoixRestaurantService } from './choix-restaurant.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Restaurant } from '../model/restaurant';

@Injectable({
  providedIn: 'root',
})
export class ValiderPanierService {
  constructor(
    private http: HttpClient,
    private restaurantService: ChoixRestaurantService
  ) {}

  public enregistrer(
    restaurant: Restaurant,
    prixTotal: string | null,
    panier: Map<string, number>
  ): Observable<any> {
    const lignesCommande: any[] = [];
    panier.forEach((value: number, key: string) => {
      let o = {
        id: {plat: JSON.parse(key)['id']['plat']},
        quantite: value,
      };
      lignesCommande.push(o);
    });
    let commande = {
      lignesCommande: lignesCommande,
      restaurant: restaurant,
      prixTotal: prixTotal as unknown as number,
    };
    return this.http.post(
      'http://localhost:8080/hashi/api/commande',
      commande,
      {
        headers: this.httpHeaders,
      }
    );
  }

  private get httpHeaders(): HttpHeaders {
    return new HttpHeaders({
      Authorization: 'Basic ' + sessionStorage.getItem('token'),
      'Content-Type': 'application/json',
    });
  }
}
