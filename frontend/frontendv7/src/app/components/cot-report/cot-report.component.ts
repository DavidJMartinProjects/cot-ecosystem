import { Component, OnInit, ViewChild } from '@angular/core';
import { CotReportService } from 'src/app/services/cot-report-backend.service';

@Component({
  selector: 'app-cot-report',
  templateUrl: './cot-report.component.html',
  styleUrls: ['./cot-report.component.scss']
})
export class CotReportComponent implements OnInit {

  symbols: string[] = ['USD', 'EUR', 'GBP', 'AUD', 'NZD', 'JPY', 'CHF', 'CAD', 'MXN', 'ZAR'];

  constructor(private cotReportService: CotReportService) {
  }

  ngOnInit(): void {
  }

  handleChange(theSymbol: any) {
    this.cotReportService.updateSymbolData(theSymbol) ;
  }

}
