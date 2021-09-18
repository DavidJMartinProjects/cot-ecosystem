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

  data: any;
  defaultSelection: string = 'USD';
  showSpinner = true;
  showLogoutButton = false;

  constructor(private http: HttpClient, private router: Router) {
    //get request from web api
    console.log('GET http://cot.com/api/reports/cot?symbol=' + this.defaultSelection)
    this.http.get(`http://cot.com/api/reports/cot?symbol=` + this.defaultSelection).subscribe((data: any) => {      
      // this.showLogoutButton = true;
      this.data = data;
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
      this.data = data;
    }, (error: any) => console.error(error));

  }

  handleChange(theSymbol: string) {    
    this.getSymbolData(theSymbol);
  }

}
