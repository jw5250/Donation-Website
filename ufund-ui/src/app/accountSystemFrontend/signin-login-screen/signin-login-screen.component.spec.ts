import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SigninLoginScreenComponent } from './signin-login-screen.component';

describe('SigninLoginScreenComponent', () => {
  let component: SigninLoginScreenComponent;
  let fixture: ComponentFixture<SigninLoginScreenComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SigninLoginScreenComponent]
    });
    fixture = TestBed.createComponent(SigninLoginScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
