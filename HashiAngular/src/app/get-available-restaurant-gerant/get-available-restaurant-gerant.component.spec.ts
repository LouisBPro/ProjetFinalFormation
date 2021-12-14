import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetAvailableRestaurantGerantComponent } from './get-available-restaurant-gerant.component';

describe('GetAvailableRestaurantGerantComponent', () => {
  let component: GetAvailableRestaurantGerantComponent;
  let fixture: ComponentFixture<GetAvailableRestaurantGerantComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GetAvailableRestaurantGerantComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GetAvailableRestaurantGerantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
