import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InscriptionCuisinierComponent } from './inscription-cuisinier.component';

describe('InscriptionCuisinierComponent', () => {
  let component: InscriptionCuisinierComponent;
  let fixture: ComponentFixture<InscriptionCuisinierComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InscriptionCuisinierComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InscriptionCuisinierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
