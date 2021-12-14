import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditCuisinierComponent } from './edit-cuisinier.component';

describe('EditCuisinierComponent', () => {
  let component: EditCuisinierComponent;
  let fixture: ComponentFixture<EditCuisinierComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditCuisinierComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditCuisinierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
