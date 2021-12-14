import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Gerant } from '../model/gerant';

@Injectable({
  providedIn: 'root',
})
export class GerantService {
  private static URL: string = 'http://localhost:8080/hashi/api/gerant';

  constructor(private http: HttpClient) {}

  private get httpHeaders(): HttpHeaders {
    return new HttpHeaders({
      Authorization: 'Basic' + sessionStorage.getItem('token'),
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*',
    });
  }

  public byId(id: number): Observable<any> {
    return this.http.get<Gerant>(`${GerantService.URL}/${id}`, {
      headers: this.httpHeaders,
    });
  }

  public local(): Observable<any> {
    return this.http.get<Gerant>(`${GerantService.URL}/local`, {
      headers: this.httpHeaders,
    });
  }

  public deleteLocal(): Observable<any> {
    return this.http.delete<Gerant[]>(`${GerantService.URL}/local `, {
      headers: this.httpHeaders,
    });
  }

  public checkLogin(login: string): Observable<boolean> {
    return this.http.get<boolean>(
      'http://localhost:8080/hashi/api/user/' + login
    );
  }

  public insert(gerant: Gerant, password: string): Observable<Gerant> {
    const o = {
      prenom: gerant.prenom,
      nom: gerant.nom,
      email: gerant.email,
      user: { login: gerant.user!.login, password: password },
      restaurants : gerant.restaurants
    };
    return this.http.post<Gerant>(GerantService.URL, o);
  }

  public updateLocal(gerant: Gerant, password: string): Observable<Gerant> {
    const o = {
      id: gerant.id,
      prenom: gerant.prenom,
      nom: gerant.nom,
      email: gerant.email,
      user: { login: gerant.user!.login, password: password },
      restaurants : gerant.restaurants
    };
    return this.http.put<Gerant>(
      `${GerantService.URL}/local`,
      o,
      {
        headers: this.httpHeaders,
      }
    );
  }
}
