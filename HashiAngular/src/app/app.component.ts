import { GerantService } from './services/gerant.service';
import { ClientService } from './services/client.service';
import { Client } from './model/client';
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'HashiAngular';

  constructor(
    private router: Router,
    private clientService: ClientService,
    private gerantService: GerantService
  ) {}

  logout() {
    sessionStorage.clear();
    localStorage.clear();
    this.router.navigate(['/home']);
  }

  get logged(): boolean {
    return !!sessionStorage.getItem('token') ? true : false;
  }

  getClient() {
    console.log(sessionStorage.getItem('id'));
    this.router.navigate(['/client']);
  }

  get roleGerant(): Boolean {
    const role = sessionStorage.getItem('role');
    if (role == 'gerant') {
      return true;
    } else {
      return false;
    }
  }
  get roleClient(): Boolean {
    const role = sessionStorage.getItem('role');
    if (role == 'client') {
      return true;
    } else {
      return false;
    }
  }
  get etatPanier(): Boolean {
    const etat = localStorage.getItem('panier');
    if (etat != '[]' && etat != null) {
      return true;
    } else {
      return false;
    }
  }
}
