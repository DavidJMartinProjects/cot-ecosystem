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
  showSpinner = true;
  showLogoutButton = false;
  view: [number, number] = [600, 250];

  constructor(private http: HttpClient, private router: Router) {
    //get request from web api
    console.log('GET http://cot.com/api/reports/cot?symbol=' + this.defaultSelection)
    this.http.get(`http://cot.com/api/reports/cot?symbol=` + this.defaultSelection).subscribe((data: any) => {      
      // this.showLogoutButton = true;
      this.tableData = data;
      this.chartData = [
        { name: "longs", value: this.tableData[0].longPositions },
        { name: "shorts", value: this.tableData[0].shortPositions }
      ];
      setTimeout(() => {
        $('#datatableexample').DataTable({
          "bPaginate": false,
          "bLengthChange": false,
          "bFilter": false,
          "bInfo": false,
          "bAutoWidth": false,
          "ordering": false,
          "fixedHeader": false
        });
      }, 10);
      this.showSpinner = false;
    }, (error: any) => console.error(error));
  }

  ngOnInit(): void {

  }

  getSymbolData(theSymbol: string) {
    console.log('GET http://cot.com/api/reports/cot?symbol=' + theSymbol)
    this.http.get('http://cot.com/api/reports/cot?symbol=' + theSymbol).subscribe((data: any) => {
    // update tableData   
    this.tableData = data;   
    // update chart data     
      this.chartData = [
        { name: "longs", value: this.tableData[0].longPositions },
        { name: "shorts", value: this.tableData[0].shortPositions }
      ];
    }, (error: any) => console.error(error));
  }

  handleChange(theSymbol: string) {    
    this.getSymbolData(theSymbol);
  }


}
