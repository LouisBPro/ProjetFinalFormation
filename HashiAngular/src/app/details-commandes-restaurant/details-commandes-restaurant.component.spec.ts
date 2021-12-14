import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsCommandesRestaurantComponent } from './details-commandes-restaurant.component';

describe('DetailsCommandesRestaurantComponent', () => {
  let component: DetailsCommandesRestaurantComponent;
  let fixture: ComponentFixture<DetailsCommandesRestaurantComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailsCommandesRestaurantComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailsCommandesRestaurantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
