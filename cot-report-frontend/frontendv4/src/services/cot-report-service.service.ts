import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CotReportServiceService {
    
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

  getSymbolData(symbol: string) {
    this.http
      .get<any>('http://my-example.com:80/api/reports/cot?symbol=' + symbol)
      .subscribe((response: string | any[]) => {
        console.log('GET: http://localhost:80/api/reports/cot?symbol=USD');
        this.data = response.slice(0, 11);
      }, (error: any) => {
        console.log(error);
      })
  }

  displayedColumns =
  [
    'reportDate',
    'longPositions',
    'shortPositions',
    'percentageLong',
    'percentageShort',
    'netPositions'
  ];

}
