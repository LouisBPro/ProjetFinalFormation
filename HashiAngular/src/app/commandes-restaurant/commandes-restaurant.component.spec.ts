import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CommandesRestaurantComponent } from './commandes-restaurant.component';

describe('CommandesRestaurantComponent', () => {
  let component: CommandesRestaurantComponent;
  let fixture: ComponentFixture<CommandesRestaurantComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CommandesRestaurantComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CommandesRestaurantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
