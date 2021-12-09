import { HomeComponent } from "./home/home.component";
import { Routes } from "@angular/router";
import { PlatsComponent } from "./plats/plats.component";
export const route: Routes = [
  { path: "home", component: HomeComponent },
  {
    path: "plats",
    component: PlatsComponent,
  },
];

];
