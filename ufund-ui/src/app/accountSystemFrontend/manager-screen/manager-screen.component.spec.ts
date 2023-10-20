import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagerScreenComponent } from './manager-screen.component';

describe('ManagerScreenComponent', () => {
  let component: ManagerScreenComponent;
  let fixture: ComponentFixture<ManagerScreenComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ManagerScreenComponent]
    });
    fixture = TestBed.createComponent(ManagerScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
