import { ClientComponent } from "./client/client/client.component";
import { AuthService } from "./services/auth.service";
import { EditClientComponent } from "./client/edit-client/edit-client.component";
import { HomeComponent } from "./home/home.component";
import { Routes } from "@angular/router";
import { PlatsComponent } from "./plats/plats.component";
import { LoginComponent } from "./login/login.component";
import { EditPlatComponent } from "./plats/edit-plat/edit-plat.component";
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
  { path: "client", component: ClientComponent },
  {
    path: "client/edit/:client",
    component: EditClientComponent,
    canActivate: [AuthService],
  },
  { path: "client/edit", component: EditClientComponent },
];
