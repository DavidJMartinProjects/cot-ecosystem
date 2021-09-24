import { HttpClient } from '@angular/common/http';
import { Component, NgModule, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { Color, ScaleType } from '@swimlane/ngx-charts';

declare var $: any;

@Component({
  selector: 'app-cot-report',
  templateUrl: './cot-report.component.html',
  styleUrls: ['./cot-report.component.scss']
})
export class CotReportComponent implements OnInit {

  defaultSelection: string = 'USD';
  tableData: any;
  chartData: any;
  seriesData: any;
  showSpinner = true;
  showLogoutButton = false;
  view: [number, number] = [500, 250];
  lineChartSize: [number, number] = [480, 210];
  // dtOptions: DataTables.Settings = {};

  // line chart options
  showXAxis = false;
  showYAxis = true;
  gradient = true;
  showLegend = false;
  xAxisLabel = 'Time';
  showYAxisLabel = true;
  showXAxisLabel = true;
  yAxisLabel = 'Net Positions';
  autoScale = true;
  legendTitle = 'Positions';
 
  multi: any[] | undefined;

  constructor(private http: HttpClient, private router: Router) {
    console.log('GET http://cot.com/api/reports/cot?symbol=' + this.defaultSelection)
    this.http.get(`http://cot.com/api/reports/cot?symbol=` + this.defaultSelection).subscribe((data: any) => {
      // this.showLogoutButton = true;
      this.tableData = data;
      this.buildPieChartData(data);
      setTimeout(() => {
        this.buildDataTable();
      }, 10);

      this.buildLineChartData(data);
      this.showSpinner = false;
    }, (error: any) => console.error(error));

  }

  ngOnInit(): void {
  }


  private buildPieChartData(data: any) {
    this.chartData = [
      { name: "shorts", value: data[0].shortPositions },
      { name: "longs", value: data[0].longPositions },
    ];
  }

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

  getSymbolData(theSymbol: string) {
    $('#datatableexample').hide();
    console.log('GET http://cot.com/api/reports/cot?symbol=' + theSymbol)
    this.http.get('http://cot.com/api/reports/cot?symbol=' + theSymbol).subscribe((data: any) => {

      // update table data
      $('#datatableexample').DataTable().destroy();
      this.tableData = data;
      setTimeout(() => {
        this.buildDataTable();
      }, 1);
      this.showSpinner = false;
      $('#datatableexample').show();

      this.buildPieChartData(data);
      this.buildLineChartData(data);
    }, (error: any) => console.error(error));



  }

  private buildDataTable() {
    $('#datatableexample').DataTable({
      "bPaginate": true,
      "bLengthChange": true,
      "lengthMenu": [6, 10, 20],
      "bFilter": false,
      "bInfo": true,
      "bAutoWidth": false,
      "ordering": false,
      "fixedHeader": false,
      "iDisplayLength": 6,
      "pageLength": 6
    });
  }

  handleChange(theSymbol: string) {
    this.getSymbolData(theSymbol);
  }


}
