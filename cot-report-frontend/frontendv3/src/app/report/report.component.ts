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

  // chart data
  pieChartData: any;  
  changeChartSize: [number, number] = [130, 180];
  
  // dataList = new MatTableDataSource();
  data: any;

  // radio-button group 
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
          table: { cols: 1, rows: 1 },
        };
      }
 
     return {
        columns: 3,
        miniCard: { cols: 3, rows: 1 },
        chart: { cols: 1, rows: 3 },
        table: { cols: 3, rows: 5 },
      };
    })
  );

  constructor(private breakpointObserver: BreakpointObserver, private http: HttpClient) {
    this.getSymbolData(this.selectedSymbol);
  }

  handleChange(theSymbol: string) {    
    this.getSymbolData(theSymbol);
  }

    multi: any[] | undefined;
    lineChartSize: [number, number] = [330, 210];
    // line chart options
    showXAxis = false;
    showYAxis = true;
    gradient = true;
    showLegend = false;
    xAxisLabel = 'Time';
    showYAxisLabel = true;
    showXAxisLabel = true;
    yAxisLabel = 'Institutional Positions';
    autoScale = true;
    legendTitle = 'Positions';
    changeLabel = '% change'
  
  
  getSymbolData(symbol: string) {    
    this.http
      .get<any>('http://my-example.com:80/api/reports/cot?symbol=' + symbol)
      .subscribe((response) => {
        console.log('GET: http://my-example.com:80/api/reports/cot?symbol=' + symbol);
        this.data = response.slice(0, 6);
        this.buildPieChartData(this.data);        
        this.buildLineChartData(this.data);
        this.buildChangeData(this.data);
      }, error => {
        console.log(error);
      })
  }

  positionChangeData: any;
  buildChangeData(data: any) {
    this.positionChangeData = [
      {
        "name": "% change ",
        "series": [
          {
            "name": "sells",
            "value": Number(data[0].percentageShortChange)
          },
          {
            "name": "buys",
            "value": Number(data[0].percentageLongChange)
          }
        ]
      }
    ]
  }

  private buildPieChartData(data: any) {
    this.pieChartData = [
      { name: "shorts", value: data[0].shortPositions },
      { name: "longs", value: data[0].longPositions },
    ];
  }

  
  // line chart
  private buildLineChartData(data: any) {
    this.multi = [    
      {
        "name": "Open Positions",
        "series": this.getOpenPositions(data)
      }
    ];
  }

  getOpenPositions(data: any) {
    const array: { name: any; value: any; }[] = [];
    data.forEach((report: { reportDate: any; netPositions: any; }) => {
      array.push({ name: report.reportDate, value: report.netPositions });
    });
    return array.reverse();
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
