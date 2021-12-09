import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Client } from '../model/client';

@Injectable({
  providedIn: 'root',
})
export class ClientService {
  private static URL: string = 'http://localhost8080/hashi/api/client';

  constructor(private http: HttpClient) {}

  private get httpHeaders(): HttpHeaders {
    return new HttpHeaders({
      Authorisation: 'Basic' + sessionStorage.getItem('token'),
      'Content-Type': 'application/json',
    });
  }
  public byId(id: number): Observable<Client> {
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
      user: { login: client.login, password: password },
      adresse: {
        numero: client.numero,
        rue: client.rue,
        codePostal: client.codePostal,
        ville: client.ville,
      },
    };
    return this.http.post<Client>(ClientService.URL, o);
  }

  public update(client: Client): Observable<Client> {
    console.log(client);
    return this.http.put<Client>(`${ClientService.URL}/${client.id}`, client, {
      headers: this.httpHeaders,
    });
  }
}
