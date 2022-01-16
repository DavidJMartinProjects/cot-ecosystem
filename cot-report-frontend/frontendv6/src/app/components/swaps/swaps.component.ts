import { Component, OnInit } from '@angular/core';
import { SwapBackendService } from 'src/app/services/swap-backend.service';

@Component({
  selector: 'app-swaps',
  templateUrl: './swaps.component.html',
  styleUrls: ['./swaps.component.scss']
})
export class SwapsComponent implements OnInit {

  symbols: string[] = ['USD', 'EUR', 'GBP', 'AUD', 'NZD', 'JPY', 'CHF', 'CAD', 'MXN', 'ZAR'];
  data: any;

  constructor(private swapBackendService: SwapBackendService) {   
    this.swapBackendService.dataSource.subscribe(data => this.data = data)
  }

  ngOnInit(): void {    
  }

  handleChange(theSymbol: string, filterPositiveSwaps: string) {    
    this.swapBackendService.updateSwapData(theSymbol, filterPositiveSwaps);
    console.log(this.data.length);
  }
  
}
