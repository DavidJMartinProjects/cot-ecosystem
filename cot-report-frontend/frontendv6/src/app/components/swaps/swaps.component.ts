import { Component, OnInit } from '@angular/core';
import { CotReportService } from 'src/app/services/cot-report-backend.service';

@Component({
  selector: 'app-swaps',
  templateUrl: './swaps.component.html',
  styleUrls: ['./swaps.component.scss']
})
export class SwapsComponent implements OnInit {

  symbols: string[] = ['USD', 'EUR', 'GBP', 'AUD', 'NZD', 'JPY', 'CHF', 'CAD', 'MXN', 'ZAR', 'GOLD', 'BTC'];

  constructor(private cotReportService: CotReportService) {   
  }

  ngOnInit(): void {    
  }

  handleChange(theSymbol: any) {    
    this.cotReportService.updateSymbolData(theSymbol) ;
  }
  
}
