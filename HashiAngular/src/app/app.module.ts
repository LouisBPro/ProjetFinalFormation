import { RouterModule, Routes } from "@angular/router";
import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";

import { AppComponent } from "./app.component";
import { HomeComponent } from "./home/home.component";
import { route } from "./route";
import { PlatsComponent } from "./plats/plats.component";

@NgModule({
  declarations: [AppComponent, HomeComponent, PlatsComponent],
  imports: [BrowserModule, RouterModule.forRoot(route)],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
