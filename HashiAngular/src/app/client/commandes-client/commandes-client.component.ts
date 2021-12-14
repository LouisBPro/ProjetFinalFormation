import { DomSanitizer } from "@angular/platform-browser";
import { Observable } from "rxjs";
import { Commande } from "./../../model/commande";
import { ActivatedRoute } from "@angular/router";
import { ClientService } from "./../../services/client.service";
import { Component, OnInit } from "@angular/core";
import { MatTableModule } from "@angular/material/table";
import { MatPaginatorModule } from "@angular/material/paginator";

@Component({
  selector: "app-commandes-client",
  templateUrl: "./commandes-client.component.html",
  styleUrls: ["./commandes-client.component.css"],
})
export class CommandesClientComponent implements OnInit {
  commandes: Observable<Commande[]> | undefined = undefined;
  commandeDetaillee: Commande = new Commande();
  afficherDetails: boolean = false;
  constructor(
    private clientService: ClientService,
    public sanitizer: DomSanitizer
  ) {}

  ngOnInit(): void {
    this.clientService.local().subscribe((c) => {
      this.commandes = this.clientService.getCommandesByLocalClient();
    });
  }

  getDetails(id: number) {
    this.clientService.getCommandesByIdLocal(id).subscribe((o) => {
      this.commandeDetaillee = o;
      this.afficherDetails = true;
    });
  }
}
