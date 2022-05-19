import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CotChartsComponent } from './cot-charts.component';

describe('CotChartsComponent', () => {
  let component: CotChartsComponent;
  let fixture: ComponentFixture<CotChartsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CotChartsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CotChartsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
