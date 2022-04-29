import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-cot-table',
  templateUrl: './cot-table.component.html',
  styleUrls: ['./cot-table.component.scss']
})
export class CotTableComponent implements OnInit {
     
  selectedSymbol: string = 'USD';
  symbols: string[] = ['USD', 'EUR', 'GBP', 'AUD', 'NZD', 'JPY', 'CHF', 'CAD', 'CAD', 'MXN', 'ZAR', 'BTC'];
  data: any;

  constructor(private http: HttpClient) {
    this.getSymbolData(this.symbols[0]);
  }

  ngOnInit(): void {
  }

  handleChange(theSymbol: string) {
    this.getSymbolData(theSymbol);
  }

  cot_report_backend_url: string = 'http://cloud-projectz.xyz/api/reports?symbol=';

  getSymbolData(symbol: string) {
    this.http
      .get<any>(this.cot_report_backend_url + symbol)
      .subscribe((response: string | any[]) => {
        console.log('GET: ' + this.cot_report_backend_url  + symbol);
        this.data = response.slice(0, 11);
      }, (error: any) => {
        console.log(error);
      })
  }

  columnNames =
  [
    'Report Date ',
    'Longs',
    'Shorts',
    '% Long',
    '% Short',
    'Net Positions'
  ];

}
