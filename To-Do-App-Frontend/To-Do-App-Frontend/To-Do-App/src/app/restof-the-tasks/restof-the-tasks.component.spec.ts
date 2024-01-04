import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RestofTheTasksComponent } from './restof-the-tasks.component';

describe('RestofTheTasksComponent', () => {
  let component: RestofTheTasksComponent;
  let fixture: ComponentFixture<RestofTheTasksComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RestofTheTasksComponent]
    });
    fixture = TestBed.createComponent(RestofTheTasksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
