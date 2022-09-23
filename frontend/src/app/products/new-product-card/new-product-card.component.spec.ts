import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewProductCardComponent } from './new-product-card.component';

describe('NewProductComponent', () => {
  let component: NewProductCardComponent;
  let fixture: ComponentFixture<NewProductCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewProductCardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewProductCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
