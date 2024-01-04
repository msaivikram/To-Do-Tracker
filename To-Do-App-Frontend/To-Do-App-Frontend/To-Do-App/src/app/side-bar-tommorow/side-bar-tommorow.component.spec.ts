import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SideBarTommorowComponent } from './side-bar-tommorow.component';

describe('SideBarTommorowComponent', () => {
  let component: SideBarTommorowComponent;
  let fixture: ComponentFixture<SideBarTommorowComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SideBarTommorowComponent]
    });
    fixture = TestBed.createComponent(SideBarTommorowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
