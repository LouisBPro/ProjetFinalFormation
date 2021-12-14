import { User } from 'src/app/model/user';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';
import { GerantService } from './../../services/gerant.service';
import {
  FormGroup,
  FormControl,
  Validators,
  AbstractControl,
  ValidationErrors,
} from '@angular/forms';
import { Gerant } from 'src/app/model/gerant';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-edit-gerant',
  templateUrl: './edit-gerant.component.html',
  styleUrls: ['./edit-gerant.component.css'],
})
export class EditGerantComponent implements OnInit {
  gerant: Gerant = new Gerant();
  password: string = '';
  _form: FormGroup;
  _edition: boolean = false;

  constructor(
    private gerantService: GerantService,
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
      login: new FormControl(''),
      passwordGroup: new FormGroup(
        {
          password: new FormControl('', [
            Validators.pattern(/^[a-zA-ZÀ-ÿ0-9]{5,}$/),
            Validators.maxLength(30),
          ]),
          confirm: new FormControl(''),
        },
        this.checkNotEquals
      ),
    });
  }

  get edition(): boolean {
    return this._edition;
  }

  get form(): { [key: string]: AbstractControl } {
    return this._form.controls;
  }

  ngOnInit(): void {
    this.gerantService.local().subscribe((gerant) => {
      this.gerant = gerant;
      this.form['prenom'].setValue(this.gerant.prenom);
      this.form['nom'].setValue(this.gerant.nom);
      this.form['email'].setValue(this.gerant.email);
      this.form['login'].setValue(this.gerant.user!.login);
    });
  }

  goEditer() {
    this._edition = true;
  }

  goAnnuler() {
    this._edition = false;
  }

  save() {
    var password;
    if (this.form['passwordGroup'].get('password')!.value == '') {
      password = '';
    } else {
      password = this.form['passwordGroup'].get('password')!.value;
    }
    this.gerantService
      .updateLocal(
        new Gerant(
          this.gerant.id,
          this.form['prenom'].value,
          this.form['nom'].value,
          this.form['email'].value,
          new User(this.form['login'].value)
        ),
        password
      )
      .subscribe(
        (client) => {
          this.goAnnuler();
        },
        (error) => {
          console.log('erreur modification compte gerant');
        }
      );
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

  deleteLocal() {
    this.gerantService.deleteLocal().subscribe((o) => {
      sessionStorage.clear();
      localStorage.clear();
      this.router.navigate(['/home']);
    });
  }
}
