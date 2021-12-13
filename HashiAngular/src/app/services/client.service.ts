import { Commande } from './../model/commande';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Client } from '../model/client';

@Injectable({
  providedIn: 'root',
})
export class ClientService {
  private static URL: string = 'http://localhost:8080/hashi/api/client';

  constructor(private http: HttpClient) {}

  private get httpHeaders(): HttpHeaders {
    return new HttpHeaders({
      Authorisation: 'Basic' + sessionStorage.getItem('token'),
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*',
    });
  }

  public byId(id: number): Observable<any> {
    return this.http.get<Client>(`${ClientService.URL}/${id}`, {
      headers: this.httpHeaders,
    });
  }
  public delete(id: number): Observable<any> {
    return this.http.delete<Client[]>(`${ClientService.URL}/${id}`, {
      headers: this.httpHeaders,
    });
  }

  public checkLogin(login: string): Observable<boolean> {
    return this.http.get<boolean>(
      'http://localhost:8080/hashi/api/user/' + login
    );
  }

  public insert(client: Client, password: string): Observable<Client> {
    const o = {
      prenom: client.prenom,
      nom: client.nom,
      email: client.email,
      user: { login: client.user!.login, password: password },
      adresse: {
        numero: client.adresse!.numero,
        rue: client.adresse!.rue,
        codePostal: client.adresse!.codePostal,
        ville: client.adresse!.ville,
      },
    };
    return this.http.post<Client>(ClientService.URL, o, {
      headers: this.httpHeaders,
    });
  }

  public update(client: Client, password: string): Observable<Client> {
    const o = {
      id: client.id,
      prenom: client.prenom,
      nom: client.nom,
      email: client.email,
      user: { login: client.user!.login, password: password },
      adresse: {
        numero: client.adresse!.numero,
        rue: client.adresse!.rue,
        codePostal: client.adresse!.codePostal,
        ville: client.adresse!.ville,
      },
    };
    console.log(o);
    return this.http.put<Client>(`${ClientService.URL}/${client.id}`, o, {
      headers: this.httpHeaders,
    });
  }

  public getCommandesByClient(client: Client): Observable<Commande[]> {
    return this.http.get<Commande[]>(
      `http://localhost:8080/hashi/api/commande/client/${client.id}`,
      {
        headers: this.httpHeaders,
      }
    );
  }

  public getCommandesById(id: number): Observable<Commande> {
    return this.http.get<Commande>(
      `http://localhost:8080/hashi/api/commande/${id}`,
      {
        headers: this.httpHeaders,
      }
    );
  }
}
