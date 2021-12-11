import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InscriptionGerantComponent } from './inscription-gerant.component';

describe('InscriptionGerantComponent', () => {
  let component: InscriptionGerantComponent;
  let fixture: ComponentFixture<InscriptionGerantComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InscriptionGerantComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InscriptionGerantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
