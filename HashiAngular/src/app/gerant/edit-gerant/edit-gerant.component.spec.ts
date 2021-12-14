import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditGerantComponent } from './edit-gerant.component';

describe('EditGerantComponent', () => {
  let component: EditGerantComponent;
  let fixture: ComponentFixture<EditGerantComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditGerantComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditGerantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
