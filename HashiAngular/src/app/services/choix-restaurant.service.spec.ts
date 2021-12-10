import { TestBed } from '@angular/core/testing';

import { ChoixRestaurantService } from './choix-restaurant.service';

describe('ChoixRestaurantService', () => {
  let service: ChoixRestaurantService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ChoixRestaurantService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
