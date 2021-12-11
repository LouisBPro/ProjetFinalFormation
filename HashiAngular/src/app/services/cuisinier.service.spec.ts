import { TestBed } from '@angular/core/testing';

import { CuisinierService } from './cuisinier.service';

describe('CuisinierService', () => {
  let service: CuisinierService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CuisinierService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
