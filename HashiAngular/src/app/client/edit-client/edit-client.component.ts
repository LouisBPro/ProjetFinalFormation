import { ClientService } from './../../services/client.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Client } from './../../model/client';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';
import { Adresse } from 'src/app/model/adresse';

@Component({
  selector: 'app-edit-client',
  templateUrl: './edit-client.component.html',
  styleUrls: ['./edit-client.component.css'],
})
export class EditClientComponent implements OnInit {
  client: Client = new Client(new User(), new Adresse());
  password: string = '';

  constructor(
    private activatedRoute: ActivatedRoute,
    private clientService: ClientService,
    private router: Router
  ) {}

  ngOnInit(): void {
    if (!!sessionStorage.getItem('id')) {
      this.clientService
        .byId(sessionStorage.getItem('id') as unknown as number)
        .subscribe((client) => {
          this.client = client;
        });
    }
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
