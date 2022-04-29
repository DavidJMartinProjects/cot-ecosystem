import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CotReportComponent } from './cot-report.component';

describe('CotReportComponent', () => {
  let component: CotReportComponent;
  let fixture: ComponentFixture<CotReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CotReportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CotReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
