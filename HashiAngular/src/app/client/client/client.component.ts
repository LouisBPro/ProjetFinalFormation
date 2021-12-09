import { ActivatedRoute } from '@angular/router';
import { Client } from './../../model/client';
import { ClientService } from './../../services/client.service';
import { Component, OnInit } from '@angular/core';

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
    this.activatedRoute.params.subscribe((params) => {
      if (!!params['client']) {
        this.clientService.byId(params['client']).subscribe((client) => {
          this.client = client;
        });
      }
    });
  }
}
