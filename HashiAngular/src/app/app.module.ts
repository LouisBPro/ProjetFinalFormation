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
import { InscriptionComponent } from './inscription/inscription.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    PlatsComponent,
    LoginComponent,
    EditClientComponent,
    ClientComponent,
    EditPlatComponent,
    InscriptionComponent
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
