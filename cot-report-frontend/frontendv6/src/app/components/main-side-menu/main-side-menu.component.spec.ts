import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MainSideMenuComponent } from './main-side-menu.component';

describe('MainSideMenuComponent', () => {
  let component: MainSideMenuComponent;
  let fixture: ComponentFixture<MainSideMenuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MainSideMenuComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MainSideMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
