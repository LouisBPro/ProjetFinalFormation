import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParametresGerantComponent } from './parametres-gerant.component';

describe('ParametresGerantComponent', () => {
  let component: ParametresGerantComponent;
  let fixture: ComponentFixture<ParametresGerantComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ParametresGerantComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ParametresGerantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
