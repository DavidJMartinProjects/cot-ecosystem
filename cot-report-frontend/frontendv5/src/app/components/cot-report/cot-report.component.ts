import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-cot-report',
  templateUrl: './cot-report.component.html',
  styleUrls: ['./cot-report.component.scss']
})
export class CotReportComponent implements OnInit {

  symbols: string[] = ['USD', 'EUR', 'GBP', 'AUD', 'NZD', 'JPY', 'CHF', 'CAD', 'CAD', 'MXN', 'ZAR', 'BTC'];
  
  constructor() { }

  ngOnInit(): void {
  }

}
