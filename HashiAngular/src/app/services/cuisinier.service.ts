import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cuisinier } from '../model/cuisinier';

@Injectable({
  providedIn: 'root',
})
export class CuisinierService {
  private static URL: string = 'http://localhost:8080/hashi/api/cuisinier';

  constructor(private http: HttpClient) {}

  private get httpHeaders(): HttpHeaders {
    return new HttpHeaders({
      Authorization: 'Basic ' + sessionStorage.getItem('token'),
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*',
    });
  }

  public byId(id: number): Observable<any> {
    return this.http.get<Cuisinier>(`${CuisinierService.URL}/${id}`, {
      headers: this.httpHeaders,
    });
  }

  public local(): Observable<any> {
    return this.http.get<Cuisinier>(`${CuisinierService.URL}/local`, {
      headers: this.httpHeaders,
    });
  }

  public deleteLocal(): Observable<any> {
    return this.http.delete<Cuisinier[]>(`${CuisinierService.URL}/local`, {
      headers: this.httpHeaders,
    });
  }

  public checkLogin(login: string): Observable<boolean> {
    return this.http.get<boolean>(
      'http://localhost:8080/hashi/api/user/' + login
    );
  }

  public allCuisinierAvailable(): Observable<Cuisinier[]>{
    return this.http.get<Cuisinier[]>(`${CuisinierService.URL}/available`, {
      headers: this.httpHeaders,
    });
  }

  public insert(cuisinier: Cuisinier, password: string): Observable<Cuisinier> {
    const o = {
      prenom: cuisinier.prenom,
      nom: cuisinier.nom,
      email: cuisinier.email,
      user: { login: cuisinier.user!.login, password: password },
      restaurant : cuisinier.restaurant
    };
    return this.http.post<Cuisinier>(CuisinierService.URL, o);
  }

  public updateLocal(cuisinier: Cuisinier, password: string): Observable<Cuisinier> {
    const o = {
      id: cuisinier.id,
      prenom: cuisinier.prenom,
      nom: cuisinier.nom,
      email: cuisinier.email,
      user: { login: cuisinier.user!.login, password: password },
      restaurant : cuisinier.restaurant
    };
    return this.http.put<Cuisinier>(
      `${CuisinierService.URL}/local`,
      o,
      {
        headers: this.httpHeaders,
      }
    );
  }
}
