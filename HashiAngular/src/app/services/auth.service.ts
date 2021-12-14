import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AuthService implements CanActivate {
  constructor(private http: HttpClient, private router: Router) {}

  public canActivate(): boolean {
    if (!!sessionStorage.getItem('token')) {
      return true;
    } else {
      this.router.navigate(['/login'], { queryParams: { error: 'auth' } });
    }
    return false;
  }

  public auth(login: string, password: string): Observable<any> {
    const httpHeaders: HttpHeaders = new HttpHeaders({
      Authorization: 'Basic ' + btoa(login + ':' + password),
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*',
    });
    return this.http.get('http://localhost:8080/hashi/api/auth', {
      headers: httpHeaders,
    });
  }
}
