import { CommandesRestaurantComponent } from './commandes-restaurant/commandes-restaurant.component';
import { DetailsCommandesRestaurantComponent } from './details-commandes-restaurant/details-commandes-restaurant.component';
import { GetAvailableRestaurantGerantComponent } from './get-available-restaurant-gerant/get-available-restaurant-gerant.component';
import { ParametresGerantComponent } from './gerant/parametres-gerant/parametres-gerant.component';
import { CreateRestaurantComponent } from './create-restaurant/create-restaurant.component';
import { InscriptionGeneraleComponent } from './inscription/inscription-generale/inscription-generale.component';
import { ChoixRestaurantComponent } from './choix-restaurant/choix-restaurant.component';
import { AuthService } from './services/auth.service';
import { HomeComponent } from './home/home.component';
import { Routes } from '@angular/router';
import { PlatsComponent } from './plats/plats.component';
import { LoginComponent } from './login/login.component';
import { EditPlatComponent } from './plats/edit-plat/edit-plat.component';
import { InscriptionClientComponent } from './inscription/inscription-client/inscription-client.component';
import { InscriptionGerantComponent } from './inscription/inscription-gerant/inscription-gerant.component';
import { InscriptionCuisinierComponent } from './inscription/inscription-cuisinier/inscription-cuisinier.component';
import { PanierComponent } from './panier/panier/panier.component';
import { ValiderComponent } from './panier/valider/valider.component';
import { DragDropComponent } from './plats/drag-drop/drag-drop.component';
import { EditCuisinierComponent } from './cuisinier/edit-cuisinier/edit-cuisinier.component';
import { GerantComponent } from "./choix-restaurant/gerant/gerant.component";
import { RecapComponent } from './panier/recap/recap.component';
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
    path: 'client',
    component: ParametresClientComponent,
    canActivate: [AuthService],
  },
  {
    path: 'gerant',
    component: ParametresGerantComponent,
    canActivate: [AuthService],
  },
  {
    path: 'cuisinier',
    component: EditCuisinierComponent,
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
    path: 'panier',
    component: PanierComponent,
    canActivate: [AuthService],
  },
  {
    path: 'detail/:id',
    component: DetailsCommandesRestaurantComponent,
    canActivate: [AuthService],
  },
  {
    path: 'restaurants/detail',
    component: CommandesRestaurantComponent,
    canActivate: [AuthService],
  },
  {
    path: 'panier/:id/choix',
    component: PanierComponent,
    canActivate: [AuthService],
  },
  {
    path: 'panier/recap',
    component: RecapComponent,
    canActivate: [AuthService],
  },
  {
    path: 'panier/valider',
    component: ValiderComponent,
    canActivate: [AuthService],
  },
  {
    path: 'plats/edit/:id',
    component: EditPlatComponent,
    canActivate: [AuthService],
  },
  {
    path: 'plats/edit',
    component: EditPlatComponent,
    canActivate: [AuthService],
  },
  {
    path: 'restaurants/create',
    component: CreateRestaurantComponent,
    canActivate: [AuthService],
  },
  {
    path: "gerant/drag/:id",
    component: DragDropComponent,
  },
  {
    path: "gerant/cartes",
    component: GerantComponent,
    canActivate: [AuthService],
  },
  {
    component:GetAvailableRestaurantGerantComponent,
    path: 'restaurants/manages',
    canActivate:[AuthService],
  }
];
