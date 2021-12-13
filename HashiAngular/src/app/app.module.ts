import { RouterModule, Routes } from "@angular/router";
import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";

import { AppComponent } from "./app.component";
import { HomeComponent } from "./home/home.component";
import { route } from "./route";
import { PlatsComponent } from "./plats/plats.component";
import { LoginComponent } from "./login/login.component";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";
import { EditClientComponent } from "./client/edit-client/edit-client.component";
import { ClientComponent } from "./client/client/client.component";
import { EditPlatComponent } from "./plats/edit-plat/edit-plat.component";
import { ChoixRestaurantComponent } from "./choix-restaurant/choix-restaurant.component";
import { ChoisirComponent } from "./choix-restaurant/choisir/choisir.component";
import { InscriptionGeneraleComponent } from "./Inscription/inscription-generale/inscription-generale.component";
import { InscriptionCuisinierComponent } from "./Inscription/inscription-cuisinier/inscription-cuisinier.component";
import { InscriptionGerantComponent } from "./Inscription/inscription-gerant/inscription-gerant.component";
import { InscriptionClientComponent } from "./Inscription/inscription-client/inscription-client.component";
import { ValiderComponent } from './panier/valider/valider.component';
import { RecapComponent } from './panier/recap/recap.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    PlatsComponent,
    LoginComponent,
    EditClientComponent,
    ClientComponent,
    EditPlatComponent,
    InscriptionGeneraleComponent,
    InscriptionGerantComponent,
    InscriptionCuisinierComponent,
    InscriptionClientComponent,
    ChoixRestaurantComponent,
    ChoisirComponent,
    RecapComponent,
    ValiderComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(route),
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
