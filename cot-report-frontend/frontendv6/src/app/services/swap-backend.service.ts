import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SwapBackendService {


  data: any[] = [];
  public dataSource = new BehaviorSubject<string[]>(new Array);

  constructor(private http : HttpClient) {
    this.updateSymbolData('USD');
  }

  ngOnInit(): void {
  }

  updateSymbolData(symbol: string) {
    this.getSymbolData(symbol);
  }

  // host: string = "my-example.com"
  // report_api_url: string = "http://"+ this.host +"/api/reports?symbol=";
  report_api_url: string = "/api/swaps";
  getSymbolData(symbol: string) {
    this.http
      .get<any>(this.report_api_url + symbol)
      .subscribe((response) => {
        console.log('GET: ' + this.report_api_url + symbol);
        this.data = response.slice(0, 6);
        this.dataSource.next(this.data);
      }, error => {
        console.log(error);
      })
  }

}
