import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-report-table',
  templateUrl: './report-table.component.html',
  styleUrls: ['./report-table.component.css']
})
export class ReportTableComponent implements OnInit {

  dataList = new MatTableDataSource();
  selectedSymbol: string = 'USD';
  symbols: string[] = ['USD', 'EUR', 'GBP', 'AUD', 'NZD', 'JPY', 'CHF', 'CAD', 'CAD', 'MXN', 'ZAR', 'BTC'];

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
      .subscribe((response) => {
        console.log('GET: http://localhost:80/api/reports/cot?symbol=USD');
        this.dataList.data = response.slice(0, 11);
      }, error => {
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
