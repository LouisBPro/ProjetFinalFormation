import {
  FormGroup,
  FormControl,
  Validators,
  AbstractControl,
  ValidationErrors,
} from '@angular/forms';
import { ClientService } from './../../services/client.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Client } from './../../model/client';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';
import { Adresse } from 'src/app/model/adresse';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-edit-client',
  templateUrl: './edit-client.component.html',
  styleUrls: ['./edit-client.component.css'],
})
export class EditClientComponent implements OnInit {
  client: Client = new Client();
  password: string = '';
  _form: FormGroup;

  constructor(
    private clientService: ClientService,
    private router: Router,
    private authService: AuthService
  ) {
    this._form = new FormGroup({
      prenom: new FormControl('', [
        Validators.required,
        Validators.pattern(/^[a-zA-ZÀ-ÿ]{1,}((\s|-)[a-zA-ZÀ-ÿ]{1,})*$/),
        Validators.maxLength(50),
      ]),
      nom: new FormControl('', [
        Validators.required,
        Validators.pattern(/^[a-zA-ZÀ-ÿ]{1,}((\s|-)[a-zA-ZÀ-ÿ]{1,})*$/),
        Validators.maxLength(50),
      ]),
      email: new FormControl('', [
        Validators.required,
        Validators.email,
        Validators.maxLength(100),
      ]),
      numero: new FormControl('', [
        Validators.required,
        Validators.pattern(/^[a-zA-Z0-9À-ÿ]{1,}((\s|-)[a-zA-Z0-9À-ÿ]{1,})*$/),
        Validators.maxLength(50),
      ]),
      rue: new FormControl('', [
        Validators.required,
        Validators.pattern(/^[a-zA-Z0-9À-ÿ]{1,}((\s|-)[a-zA-Z0-9À-ÿ]{1,})*$/),
        Validators.maxLength(200),
      ]),
      codePostal: new FormControl('', [
        Validators.required,
        Validators.pattern(/^[a-zA-Z0-9À-ÿ]{1,}((\s|-)[a-zA-Z0-9À-ÿ]{1,})*$/),
        Validators.maxLength(50),
      ]),
      ville: new FormControl('', [
        Validators.required,
        Validators.pattern(/^[a-zA-ZÀ-ÿ]{1,}((\s|-)[a-zA-ZÀ-ÿ]{1,})*$/),
        Validators.maxLength(100),
      ]),
      login: new FormControl(''),
      passwordGroup: new FormGroup(
        {
          password: new FormControl('', [
            // Validators.pattern(/^[a-zA-ZÀ-ÿ0-9]{5,}$/),
            // Validators.maxLength(30),
          ]),
          confirm: new FormControl(''),
        },
        this.checkNotEquals
      ),
    });
  }

  get form(): { [key: string]: AbstractControl } {
    return this._form.controls;
  }

  ngOnInit(): void {
    if (!!sessionStorage.getItem('id')) {
      this.clientService
        .byId(sessionStorage.getItem('id') as unknown as number)
        .subscribe((client) => {
          this.client = client;
          this.form['prenom'].setValue(this.client.prenom);
          this.form['nom'].setValue(this.client.nom);
          this.form['email'].setValue(this.client.email);
          this.form['login'].setValue(this.client.user!.login);
          this.form['numero'].setValue(this.client.adresse!.numero);
          this.form['rue'].setValue(this.client.adresse!.rue);
          this.form['codePostal'].setValue(this.client.adresse!.codePostal);
          this.form['ville'].setValue(this.client.adresse!.ville);
        });
    }
  }

  save() {
    this.clientService
      .update(
        new Client(
          new User(this.form['login'].value),

          new Adresse(
            this.form['numero'].value,
            this.form['rue'].value,
            this.form['codePostal'].value,
            this.form['ville'].value
          ),
          this.client.id,
          this.form['prenom'].value,
          this.form['nom'].value,
          this.form['email'].value
        ),
        this.form['passwordGroup'].get('password')!.value
      )
      .subscribe(
        (client) => {
          this.authService
            .auth(
              this.form['login'].value,
              this.form['passwordGroup'].get('password')!.value
            )
            .subscribe((ok) => {
              sessionStorage.setItem(
                'token',
                btoa(
                  this.form['login'].value +
                    ':' +
                    this.form['passwordGroup'].get('password')!.value
                )
              );
              sessionStorage.setItem('login', this.form['login'].value);
              sessionStorage.setItem('id', ok['personne']['id']);

              // TODO roles
              if (!!ok['client']) {
                sessionStorage.setItem('role', 'client');
              } else {
                sessionStorage.setItem('role', 'admin');
              }
              if (!!localStorage.getItem('valider')) {
                localStorage.removeItem('valider');
                this.router.navigate(['/valider']);
              } else {
                this.router.navigate(['/home']);
              }
            });
        },
        (error) => {
          console.log('erreur modification compte client');
        }
      );
  }

  goHome() {
    this.router.navigate(['/client/' + this.client.id]);
  }

  checkNotEquals(group: AbstractControl): ValidationErrors | null {
    let formGroup: FormGroup = group as FormGroup;
    if (formGroup.controls['password'].errors) {
      return null;
    }
    return formGroup.controls['password'].value !=
      formGroup.controls['confirm'].value
      ? { checkNotEquals: true }
      : null;
  }
}
