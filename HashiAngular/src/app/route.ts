import { InscriptionGeneraleComponent } from './Inscription/inscription-generale/inscription-generale.component';
import { ChoisirComponent } from "./choix-restaurant/choisir/choisir.component";
import { ChoixRestaurantComponent } from "./choix-restaurant/choix-restaurant.component";
import { ClientComponent } from "./client/client/client.component";
import { AuthService } from "./services/auth.service";
import { EditClientComponent } from "./client/edit-client/edit-client.component";
import { HomeComponent } from "./home/home.component";
import { Routes } from "@angular/router";
import { PlatsComponent } from "./plats/plats.component";
import { LoginComponent } from "./login/login.component";
import { EditPlatComponent } from "./plats/edit-plat/edit-plat.component";
import { InscriptionClientComponent } from './Inscription/inscription-client/inscription-client.component';
import { InscriptionGerantComponent } from './Inscription/inscription-gerant/inscription-gerant.component';
import { InscriptionCuisinierComponent } from './Inscription/inscription-cuisinier/inscription-cuisinier.component';

export const route: Routes = [
  { path: "home", component: HomeComponent },
  {
    path: "plats",
    component: PlatsComponent,
    canActivate: [AuthService],
  },
  {
    path: "plats/edit",
    component: EditPlatComponent,
    canActivate: [AuthService],
  },
  {
    path: "plats/edit/:id",
    component: EditPlatComponent,
    canActivate: [AuthService],
  },
  { path: "login", component: LoginComponent },
  { path: "", redirectTo: "home", pathMatch: "full" },
  {
    path: "client/:client",
    component: ClientComponent,
    canActivate: [AuthService],
  },
  {
    path: "client/edit/:client",
    component: EditClientComponent,
    canActivate: [AuthService],
  },
  {
    path: "client/edit",
    component: EditClientComponent,
    canActivate: [AuthService],
  },
  { path: "inscription", component: InscriptionGeneraleComponent },
  { path: "inscription/client", component: InscriptionClientComponent },
  { path: "inscription/gerant", component: InscriptionGerantComponent },
  { path: "inscription/cuisinier", component: InscriptionCuisinierComponent },
  {
    path: "restaurants",
    component: ChoixRestaurantComponent,
    canActivate: [AuthService],
  },
  {
    path: "restaurants/choisir",
    component: ChoisirComponent,
    canActivate: [AuthService],
  },
  {
    path: "restaurants/choisir/:id",
    component: ChoisirComponent,
    canActivate: [AuthService],
  },
];
