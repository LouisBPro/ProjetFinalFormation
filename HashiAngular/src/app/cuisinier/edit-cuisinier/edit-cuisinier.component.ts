import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';
import { CuisinierService } from './../../services/cuisinier.service';
import {
  FormGroup,
  FormControl,
  Validators,
  AbstractControl,
  ValidationErrors,
} from '@angular/forms';
import { Cuisinier } from 'src/app/model/cuisinier';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';

@Component({
  selector: 'app-edit-cuisinier',
  templateUrl: './edit-cuisinier.component.html',
  styleUrls: ['./edit-cuisinier.component.css'],
})
export class EditCuisinierComponent implements OnInit {
  cuisinier: Cuisinier = new Cuisinier();
  password: string = '';
  _form: FormGroup;
  _edition: boolean = false;

  constructor(
    private cuisinierService: CuisinierService,
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
    this.cuisinierService.local().subscribe((cuisinier) => {
      this.cuisinier = cuisinier;
      this.form['prenom'].setValue(this.cuisinier.prenom);
      this.form['nom'].setValue(this.cuisinier.nom);
      this.form['email'].setValue(this.cuisinier.email);
      this.form['login'].setValue(this.cuisinier.user!.login);
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
    this.cuisinierService
      .updateLocal(
        new Cuisinier(
          this.cuisinier.id,
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
    this.cuisinierService.deleteLocal().subscribe((o) => {
      sessionStorage.clear();
      localStorage.clear();
      this.router.navigate(['/home']);
    });
  }
}
