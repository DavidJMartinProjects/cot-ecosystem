import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CotTableComponent } from './cot-table.component';

describe('CotTableComponent', () => {
  let component: CotTableComponent;
  let fixture: ComponentFixture<CotTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CotTableComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CotTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
