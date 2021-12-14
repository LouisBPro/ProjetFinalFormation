import { CommandesClientComponent } from './client/commandes-client/commandes-client.component';
import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { route } from './route';
import { PlatsComponent } from './plats/plats.component';
import { LoginComponent } from './login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { EditClientComponent } from './client/edit-client/edit-client.component';
import { EditPlatComponent } from './plats/edit-plat/edit-plat.component';
import { ChoixRestaurantComponent } from './choix-restaurant/choix-restaurant.component';
import { ChoisirComponent } from './choix-restaurant/choisir/choisir.component';
import { InscriptionGeneraleComponent } from './inscription/inscription-generale/inscription-generale.component';
import { InscriptionCuisinierComponent } from './inscription/inscription-cuisinier/inscription-cuisinier.component';
import { InscriptionGerantComponent } from './inscription/inscription-gerant/inscription-gerant.component';
import { InscriptionClientComponent } from './inscription/inscription-client/inscription-client.component';
import { RecapComponent } from './panier/recap/recap.component';
import { ValiderComponent } from './panier/valider/valider.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PanierComponent } from './panier/panier/panier.component';
import { ParametresClientComponent } from './client/parametres-client/parametres-client.component';
import { DragDropComponent } from './plats/drag-drop/drag-drop.component';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { EditGerantComponent } from './gerant/edit-gerant/edit-gerant.component';
import { ParametresGerantComponent } from './gerant/parametres-gerant/parametres-gerant.component';
import { CreateRestaurantComponent } from './create-restaurant/create-restaurant.component';
import { EditCuisinierComponent } from './cuisinier/edit-cuisinier/edit-cuisinier.component';
import { CommandesRestaurantComponent } from './commandes-restaurant/commandes-restaurant.component';
import { DetailsCommandesRestaurantComponent } from './details-commandes-restaurant/details-commandes-restaurant.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    PlatsComponent,
    LoginComponent,
    EditClientComponent,
    EditPlatComponent,
    InscriptionGeneraleComponent,
    InscriptionGerantComponent,
    InscriptionCuisinierComponent,
    InscriptionClientComponent,
    ChoixRestaurantComponent,
    ChoisirComponent,
    RecapComponent,
    ValiderComponent,
    PanierComponent,
    ParametresClientComponent,
    CommandesClientComponent,
    DragDropComponent,
    EditGerantComponent,
    ParametresGerantComponent,
    CreateRestaurantComponent,
    EditCuisinierComponent,
    CommandesRestaurantComponent,
    DetailsCommandesRestaurantComponent,
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(route),
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    DragDropModule,
    BrowserAnimationsModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
