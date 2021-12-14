import { Commande } from './../model/commande';
import { LigneCarte } from './../model/ligne-carte';
import { Restaurant } from './../model/restaurant';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ChoixRestaurantService {
  url: string = 'http://localhost:8080/hashi/api/restaurant';
  constructor(private http: HttpClient) {}
  private get httpHeaders(): HttpHeaders {
    return new HttpHeaders({
      Authorization: 'Basic ' + sessionStorage.getItem('token'),
      'Content-Type': 'application/json',
    });
  }

  public allRestaurant(): Observable<Restaurant[]> {
    return this.http.get<Restaurant[]>(this.url);
  }

  public getById(id: number): Observable<Restaurant> {
    return this.http.get<Restaurant>(this.url + '/' + id);
  }

  public getPlatById(id: number): Observable<LigneCarte[]> {
    return this.http.get<LigneCarte[]>(this.url + '/ligneCarte/' + id, {
      headers: this.httpHeaders,
    });
  }

  public insert(restaurant: Restaurant): Observable<Restaurant> {
    const o = {
      nom: restaurant.nom,
      adresse: {
        numero: restaurant.adresse!.numero,
        rue: restaurant.adresse!.rue,
        codePostal: restaurant.adresse!.codePostal,
        ville: restaurant.adresse!.ville,
      },
      // gerant: restaurant.gerant @AuthenticationPrincipal dans le rest controller
      cuisiniers: restaurant.cuisiniers,
    };
    return this.http.post<Restaurant>(this.url, o, {
      headers: this.httpHeaders,
    });
  }

  public getCommandesByRestaurant(id: number) {
    return this.http.get<Commande[]>(
      'http://localhost:8080/hashi/api/commande/restaurant/' + id,
      {
        headers: this.httpHeaders,
      }
    );
  }

  public getCommandesById(id: number) {
    return this.http.get<Commande>(
      'http://localhost:8080/hashi/api/commande/' + id,
      {
        headers: this.httpHeaders,
      }
    );
  }
}
