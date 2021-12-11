import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InscriptionGeneraleComponent } from './inscription-generale.component';

describe('InscriptionGeneraleComponent', () => {
  let component: InscriptionGeneraleComponent;
  let fixture: ComponentFixture<InscriptionGeneraleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InscriptionGeneraleComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InscriptionGeneraleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
