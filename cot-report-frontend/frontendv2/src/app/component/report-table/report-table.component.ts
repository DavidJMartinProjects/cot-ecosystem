import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-report-table',
  templateUrl: './report-table.component.html',
  styleUrls: ['./report-table.component.css']
})
export class ReportTableComponent implements OnInit {

  selectedSymbol: string = 'USD';
  symbols: string[] = ['USD', 'EUR', 'GBP', 'AUD', 'JPY', 'CHF', 'CAD', 'NAD', 'MXN', 'ZAR', 'BTC'];
  
  constructor() { }

  ngOnInit(): void {
  }

  handleChange(theSymbol: string) {
    // this.getSymbolData(theSymbol);
  }


}
