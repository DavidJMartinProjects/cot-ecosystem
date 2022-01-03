import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { CotReportService } from 'src/app/services/cotReportService.service';


@Component({
  selector: 'app-cot-report',
  templateUrl: './cot-report.component.html',
  styleUrls: ['./cot-report.component.scss']
})
export class CotReportComponent implements OnInit {

  symbols: string[] = ['USD', 'EUR', 'GBP', 'AUD', 'NZD', 'JPY', 'CHF', 'CAD', 'MXN', 'ZAR', 'GOLD', 'BTC'];
  
  constructor(private cotReportService: CotReportService) {         
  }

  ngOnInit(): void {    
  }

  handleChange(theSymbol: any) {
    console.log('SELECTED: ' + theSymbol)
    this.cotReportService.updateSymbolData(theSymbol) ;
  }

}
