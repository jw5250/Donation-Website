import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HelperChoiceInterfaceComponent } from './helper-choice-interface.component';

describe('HelperChoiceInterfaceComponent', () => {
  let component: HelperChoiceInterfaceComponent;
  let fixture: ComponentFixture<HelperChoiceInterfaceComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HelperChoiceInterfaceComponent]
    });
    fixture = TestBed.createComponent(HelperChoiceInterfaceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
