import { LigneCarte } from "./../model/ligne-carte";
import { Restaurant } from "./../model/restaurant";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable({
  providedIn: "root",
})
export class ChoixRestaurantService {
  url: string = "http://localhost:8080/hashi/api/restaurant";
  constructor(private http: HttpClient) {}
  private get httpHeaders(): HttpHeaders {
    return new HttpHeaders({
      Authorization: "Basic " + sessionStorage.getItem("token"),
      "Content-Type": "application/json",
    });
  }
  public allRestaurant(): Observable<Restaurant[]> {
    return this.http.get<Restaurant[]>(this.url, { headers: this.httpHeaders });
  }
  public getById(id: number): Observable<Restaurant> {
    return this.http.get<Restaurant>(this.url + "/" + id, {
      headers: this.httpHeaders,
    });
  }
  public getPlatById(id: number): Observable<LigneCarte[]> {
    return this.http.get<LigneCarte[]>(this.url + "/ligneCarte/" + id, {
      headers: this.httpHeaders,
    });
  }
}
