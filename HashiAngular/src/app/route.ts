import { RecapComponent } from './panier/recap/recap.component';
import { InscriptionGeneraleComponent } from './inscription/inscription-generale/inscription-generale.component';
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
import { InscriptionClientComponent } from './inscription/inscription-client/inscription-client.component';
import { InscriptionGerantComponent } from './inscription/inscription-gerant/inscription-gerant.component';
import { InscriptionCuisinierComponent } from './inscription/inscription-cuisinier/inscription-cuisinier.component';
import { PanierComponent } from './panier/panier/panier.component';
import { ValiderComponent } from './panier/valider/valider.component';
import { ParametresClientComponent } from './client/parametres-client/parametres-client.component';

export const route: Routes = [
  { path: 'home', component: HomeComponent },
  {
    path: 'plats',
    component: PlatsComponent,
    canActivate: [AuthService],
  },
  {
    path: 'plats/edit',
    component: EditPlatComponent,
    canActivate: [AuthService],
  },
  {
    path: 'plats/edit/:id',
    component: EditPlatComponent,
    canActivate: [AuthService],
  },
  { path: 'login', component: LoginComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  {
    path: 'client/:client',
    component: ParametresClientComponent,
    canActivate: [AuthService],
  },
  {
    path: 'client/edit/:client',
    component: EditClientComponent,
    canActivate: [AuthService],
  },
  {
    path: 'client/edit',
    component: EditClientComponent,
    canActivate: [AuthService],
  },
  { path: 'inscription', component: InscriptionGeneraleComponent },
  { path: 'inscription/client', component: InscriptionClientComponent },
  { path: 'inscription/gerant', component: InscriptionGerantComponent },
  { path: 'inscription/cuisinier', component: InscriptionCuisinierComponent },
  {
    path: 'restaurants',
    component: ChoixRestaurantComponent,
    canActivate: [AuthService],
  },
  {
    path: "panier",
    component: PanierComponent,
    canActivate: [AuthService],
  },
  {
    path: "panier/:id/choix",
    component: PanierComponent,
    canActivate: [AuthService],
  },
  {
    path:"panier/:id/recap",
    component : RecapComponent,
    canActivate : [AuthService]
  },
  {
    path:"panier/:id/valider",
    component : ValiderComponent,
    canActivate : [AuthService]
  }
];
