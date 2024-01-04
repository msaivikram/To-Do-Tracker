import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SideBarRestComponent } from './side-bar-rest.component';

describe('SideBarRestComponent', () => {
  let component: SideBarRestComponent;
  let fixture: ComponentFixture<SideBarRestComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SideBarRestComponent]
    });
    fixture = TestBed.createComponent(SideBarRestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
