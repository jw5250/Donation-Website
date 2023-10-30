import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CupboardComponent } from './cupboard.component';

describe('CupboardComponent', () => {
  let component: CupboardComponent;
  let fixture: ComponentFixture<CupboardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CupboardComponent]
    });
    fixture = TestBed.createComponent(CupboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
