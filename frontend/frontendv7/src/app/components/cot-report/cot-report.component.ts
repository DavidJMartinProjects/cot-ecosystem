import { Component, OnInit, ViewChild } from '@angular/core';
import { CotReportService } from 'src/app/services/cot-report-backend.service';
import { Chart, ChartType } from 'chart.js';

@Component({
  selector: 'app-cot-report',
  templateUrl: './cot-report.component.html',
  styleUrls: ['./cot-report.component.scss']
})
export class CotReportComponent implements OnInit {

  symbols: string[] = ['USD', 'EUR', 'GBP', 'AUD', 'NZD', 'JPY', 'CHF', 'CAD', 'MXN', 'ZAR'];
  symbolFullName: string= ''
  data: any;

  constructor(private cotReportService: CotReportService) {
  }

  ngOnInit(): void {
    this.cotReportService.symbolFullName.subscribe((fullname) => {
      this.symbolFullName = fullname;
    });
    this.cotReportService.dataSource.subscribe((data) => {
      this.data = data;
    });

  }

  handleChange(theSymbol: any) {
    this.cotReportService.updateSymbolData(theSymbol) ;
  }

}
