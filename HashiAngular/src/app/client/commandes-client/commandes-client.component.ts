import { DomSanitizer } from '@angular/platform-browser';
import { Observable } from 'rxjs';
import { Commande } from './../../model/commande';
import { ActivatedRoute } from '@angular/router';
import { ClientService } from './../../services/client.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-commandes-client',
  templateUrl: './commandes-client.component.html',
  styleUrls: ['./commandes-client.component.css'],
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
    if (!!sessionStorage.getItem('id')) {
      this.clientService
        .byId(sessionStorage.getItem('id') as unknown as number)
        .subscribe((c) => {
          this.commandes = this.clientService.getCommandesByClient(c);
        });
    }
  }

  getDetails(id: number) {
    this.clientService.getCommandesById(id).subscribe((o) => {
      this.commandeDetaillee = o;
      this.afficherDetails = true;
    });
  }
}
