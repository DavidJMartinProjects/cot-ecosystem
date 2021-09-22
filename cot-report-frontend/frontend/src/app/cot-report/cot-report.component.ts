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
      }, 10);

      this.showSpinner = false;
    }, (error: any) => console.error(error));
  }

  ngOnInit(): void {

  }
  
  getSymbolData(theSymbol: string) {
    $('#datatableexample').hide();
    console.log('GET http://cot.com/api/reports/cot?symbol=' + theSymbol)
    this.http.get('http://cot.com/api/reports/cot?symbol=' + theSymbol).subscribe((data: any) => {

    // update tableData   
 

    $('#datatableexample').DataTable().destroy();
    this.tableData = data;

    setTimeout(() => {
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
    }, 1);

    this.showSpinner = false;
    $('#datatableexample').show();

    // update chart data     
      this.chartData = [
        { name: "shorts", value: this.tableData[0].shortPositions },
        { name: "longs", value: this.tableData[0].longPositions }
      ];
    }, (error: any) => console.error(error));    

  }

  handleChange(theSymbol: string) {    
    this.getSymbolData(theSymbol);
  }


}
