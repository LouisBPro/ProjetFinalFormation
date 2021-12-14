import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from './../services/auth.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  login: string = '';
  password: string = '';
  showMessage = false;
  message = '';

  constructor(
    private authService: AuthService,
    private router: Router,
    private activatedroute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedroute.queryParams.subscribe((params) => {
      if (!!params['error']) {
        if (params['error']) {
          this.message = 'authentification requise';
          this.showMessage = true;
        }
      }
    });
  }

  check() {
    this.authService.auth(this.login, this.password).subscribe(
      (ok) => {
        this.showMessage = false;
        sessionStorage.setItem('token', btoa(this.login + ':' + this.password));
        sessionStorage.setItem('login', this.login);
        sessionStorage.setItem('id', ok['personne']['id']);

        switch (ok['roles'][0]) {
          case 'ROLE_CLIENT':
            sessionStorage.setItem('role', 'client');
            break;
          case 'ROLE_CUISINIER':
            sessionStorage.setItem('role', 'cuisinier');
            break;
          case 'ROLE_GERANT':
            sessionStorage.setItem('role', 'gerant');
            break;
          case 'ROLE_ADMIN':
            sessionStorage.setItem('role', 'admin');
            break;
          default:
            sessionStorage.setItem('role', 'none');
            break;
        }
        this.router.navigate(['/home']);
      },
      (error) => {
        this.message = "erreur d'authentification";
        this.showMessage = true;
      }
    );
  }
}
