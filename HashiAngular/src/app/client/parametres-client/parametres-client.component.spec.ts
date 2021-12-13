import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParametresClientComponent } from './parametres-client.component';

describe('ParametresClientComponent', () => {
  let component: ParametresClientComponent;
  let fixture: ComponentFixture<ParametresClientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ParametresClientComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ParametresClientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
