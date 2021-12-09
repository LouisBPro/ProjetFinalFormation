import { ClientService } from './../../services/client.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Client } from './../../model/client';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-edit-client',
  templateUrl: './edit-client.component.html',
  styleUrls: ['./edit-client.component.css'],
})
export class EditClientComponent implements OnInit {
  client: Client = new Client();
  password: string = '';

  constructor(
    private activatedRoute: ActivatedRoute,
    private clientService: ClientService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.activatedRoute.params.subscribe((params) => {
      if (!!params['client']) {
        this.clientService.byId(params['client']).subscribe((client) => {
          this.client = client;
        });
      }
    });
  }

  save() {
    if (!!this.client.id) {
      this.clientService.update(this.client).subscribe((result) => {
        this.goHome();
      });
    } else {
      this.clientService
        .insert(this.client, this.password)
        .subscribe((result) => {
          this.goHome();
        });
    }
  }

  goHome() {
    this.router.navigate(['/client']);
  }
}
