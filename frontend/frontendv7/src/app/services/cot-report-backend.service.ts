import { HttpClient } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable()
export class CotReportService implements OnInit {
  data: any[] = [];
  public dataSource = new BehaviorSubject<string[]>(new Array());
  public symbolFullName = new BehaviorSubject<string>('');

  constructor(private http: HttpClient) {
    this.updateSymbolData('USD');
  }

  ngOnInit(): void {}

  updateSymbolData(symbol: string) {
    this.getSymbolData(symbol);
  }

  // report_api_url: string = "/api/reports?symbol=";
  report_api_url: string = 'http://localhost:8080/api/reports?symbol=';
  getSymbolData(symbol: string) {
    this.http.get<any>(this.report_api_url + symbol).subscribe(
      (response) => {
        console.log('GET: ' + this.report_api_url + symbol);
        this.data = response.slice(0, 10);
        this.dataSource.next(this.data);
        this.symbolFullName.next(this.getFullName(symbol));
      },
      (error) => {
        console.log(error);
      }
    );
  }

  getFullName(symbol: string): string {
    console.log('symbol: ' + symbol);
    switch (symbol) {
      case 'USD':
        return 'United States Dollar';
      case 'EUR':
        return 'Euro';
      case 'GBP':
        return 'Great British Pound';
      case 'AUD':
        return 'Australian Dollar';
      case 'NZD':
        return 'New Zeland Dollar';
      case 'JPY':
        return 'Japanese Yen';
      case 'CHF':
        return 'Swiss Franc';
      case 'CAD':
        return 'Canadian Dollar';
      case 'MXN':
        return 'Mexican Dollar';
      case 'ZAR':
        return 'South African Rand';
    }
    return symbol;
  }
}
