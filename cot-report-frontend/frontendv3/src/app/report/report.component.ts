import { Component } from '@angular/core';
import { map } from 'rxjs/operators';
import { Breakpoints, BreakpointObserver } from '@angular/cdk/layout';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.scss']
})
export class ReportComponent {

  
  // dataList = new MatTableDataSource();
  selectedSymbol: string = 'USD';
  symbols: string[] = ['USD', 'EUR', 'GBP', 'AUD', 'NZD', 'JPY', 'CHF', 'CAD', 'CAD', 'MXN', 'ZAR', 'BTC'];



  /** Based on the screen size, switch from standard to one column per row */
  cardLayout = this.breakpointObserver.observe(Breakpoints.Handset).pipe(
    map(({ matches }) => {
      if (matches) {
        return {
          columns: 1,
          miniCard: { cols: 1, rows: 1 },
          chart: { cols: 1, rows: 2 },
          table: { cols: 1, rows: 4 },
        };
      }
 
     return {
        columns: 4,
        miniCard: { cols: 4, rows: 1 },
        chart: { cols: 1, rows: 3 },
        table: { cols: 4, rows: 5 },
      };
    })
  );

  constructor(private breakpointObserver: BreakpointObserver, private http: HttpClient) {}

  handleChange(theSymbol: string) {
    this.getSymbolData(theSymbol);
  }

  
  getSymbolData(symbol: string) {
    this.http
      .get<any>('http://my-example.com:80/api/reports/cot?symbol=' + symbol)
      .subscribe((response) => {
        console.log('GET: http://localhost:80/api/reports/cot?symbol=USD');
        // this.dataList.data = response.slice(0, 11);
      }, error => {
        console.log(error);
      })
  }
}
