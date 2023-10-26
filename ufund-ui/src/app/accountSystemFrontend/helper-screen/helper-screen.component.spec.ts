import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HelperScreenComponent } from './helper-screen.component';

describe('HelperScreenComponent', () => {
  let component: HelperScreenComponent;
  let fixture: ComponentFixture<HelperScreenComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HelperScreenComponent]
    });
    fixture = TestBed.createComponent(HelperScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
