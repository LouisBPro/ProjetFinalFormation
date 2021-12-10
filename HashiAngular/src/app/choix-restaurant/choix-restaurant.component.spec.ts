import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChoixRestaurantComponent } from './choix-restaurant.component';

describe('ChoixRestaurantComponent', () => {
  let component: ChoixRestaurantComponent;
  let fixture: ComponentFixture<ChoixRestaurantComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChoixRestaurantComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChoixRestaurantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
