import { HttpClient } from '@angular/common/http';
import { Component, NgModule, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';

declare var $: any;

@Component({
  selector: 'app-cot-report',
  templateUrl: './cot-report.component.html',
  styleUrls: ['./cot-report.component.css']
})
export class CotReportComponent implements OnInit {

  data: any;
  selection: string = 'AUD';
  showSpinner = true;
  showLogoutButton = false;

  constructor(private http: HttpClient, private router: Router) {
    //get request from web api
    console.log('GET http://cot.com/api/reports/cot?symbol=AUD')
    this.http.get(`http://cot.com/api/reports/cot?symbol=AUD`).subscribe((data: any) => {
      this.data = data;
      this.showSpinner = false;
      this.showLogoutButton = true;
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
      }, 1);

      
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
