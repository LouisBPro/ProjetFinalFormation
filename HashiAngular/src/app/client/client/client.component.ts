import { Adresse } from './../../model/adresse';
import { ActivatedRoute } from '@angular/router';
import { Client } from './../../model/client';
import { ClientService } from './../../services/client.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css'],
})
export class ClientComponent implements OnInit {
  client: Client = new Client();

  constructor(
    private clientService: ClientService,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.initClient();
  }

  initClient() {
    if (!!sessionStorage.getItem('id')) {
      this.clientService
        .byId(sessionStorage.getItem('id') as unknown as number)
        .subscribe((c) => {
          this.client = c;
          // this.client.login = JSON.stringify(c['user']['login']);
          // this.client.numero = JSON.stringify(
          //   c['adresse']['numero']
          // ) as unknown as number;
          // this.client.rue = JSON.stringify(c['adresse']['rue']) as string;
          // this.client.codePostal = JSON.stringify(c['adresse']['codePostal']);
          // this.client.ville = JSON.stringify(c['adresse']['ville']);
        });
    }
  }
}
