import { HomeComponent } from "./home/home.component";
import { Routes } from "@angular/router";
import { PlatsComponent } from "./plats/plats.component";
import { LoginComponent } from './login/login.component';
export const route: Routes = [
  { path: "home", component: HomeComponent },
  {
    path: "plats",
    component: PlatsComponent,
  },
  { path: 'login', component: LoginComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
];
