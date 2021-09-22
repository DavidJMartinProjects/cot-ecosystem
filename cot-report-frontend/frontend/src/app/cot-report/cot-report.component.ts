import { HttpClient } from '@angular/common/http';
import { Component, NgModule, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';

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
  view: [number, number] = [600, 250];
  // dtOptions: DataTables.Settings = {};

  // line chart options
  showXAxis = false;
  showYAxis = true;
  gradient = false;
  showLegend = true;
  showXAxisLabel = true;
  xAxisLabel = 'Time';
  showYAxisLabel = true;
  yAxisLabel = 'Position Size';
  autoScale = true;

  multi: any[] | undefined;

  constructor(private http: HttpClient, private router: Router) {
    console.log('GET http://cot.com/api/reports/cot?symbol=' + this.defaultSelection)
    this.http.get(`http://cot.com/api/reports/cot?symbol=` + this.defaultSelection).subscribe((data: any) => {
      // this.showLogoutButton = true;
      this.tableData = data;
      this.chartData = [
        { name: "shorts", value: this.tableData[0].shortPositions },
        { name: "longs", value: this.tableData[0].longPositions },
      ];
      setTimeout(() => {
        this.buildDataTable();
      }, 10);

      console.log(data);

      this.multi = [
        {
          "name": "Long",
          "series": this.getLongPositionsData(data)
        },
        {
          "name": "Short",
          "series": this.getShortPositionsData(data)
        }
      ];

      this.showSpinner = false;
    }, (error: any) => console.error(error));

  }
  getShortPositionsData(data: any) {
    const array: { name: any; value: any; }[] = [];
    data.forEach((report: { reportDate: any; shortPositions: any; }) => {
      array.push({ name: report.reportDate, value: report.shortPositions });
    });
    return array;
  }

  getLongPositionsData(data: any) {
    const array: { name: any; value: any; }[] = [];
    data.forEach((report: { reportDate: any; longPositions: any; }) => {
      array.push({ name: report.reportDate, value: report.longPositions });
    });
    return array;
  }

  ngOnInit(): void {
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

      // update pie chart data      
      this.chartData = [
        { name: "shorts", value: this.tableData[0].shortPositions },
        { name: "longs", value: this.tableData[0].longPositions }
      ];

      this.multi = [
        {
          "name": "Longs",
          "series": this.getLongPositionsData(data)
        },
        {
          "name": "Shorts",
          "series": this.getShortPositionsData(data)
        }
      ];

    }, (error: any) => console.error(error));

    // update line chart data


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
