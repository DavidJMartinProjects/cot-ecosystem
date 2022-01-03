import { HttpClient } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable()
export class CotReportService implements OnInit {

  data: any[] = [];
  public messageSource = new BehaviorSubject<string>('');
  public dataSource = new BehaviorSubject<string[]>(new Array);
    
  constructor(private http : HttpClient) {              
    // this.updateSymbolData('USD');
  }

  ngOnInit(): void {
    this.updateSymbolData('USD');
  }

  updateSymbolData(symbol: string) {
    this.handleChange(symbol);
    this.dataSource.next(this.data);
    this.messageSource.next(symbol);
  }

  handleChange(theSymbol: any) {    
    this.getSymbolData(theSymbol);
  }

  report_api_url: string = "http://www.cloud-projectz.xyz/api/reports?symbol=";
  getSymbolData(symbol: string) {        
    this.http
      .get<any>(this.report_api_url + symbol)
      .subscribe((response) => {
        console.log('GET: ' + this.report_api_url + symbol);
        this.data = response.slice(0, 6);   
   
      }, error => {
        console.log(error);
      })                      
  }


}
