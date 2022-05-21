import { Component, OnInit } from '@angular/core';
import { CotReportService } from 'src/app/services/cot-report-backend.service';
import { SwapBackendService } from 'src/app/services/swap-backend.service';

@Component({
  selector: 'app-swaps',
  templateUrl: './swaps.component.html',
  styleUrls: ['./swaps.component.scss']
})
export class SwapsComponent implements OnInit {

  symbols: string[] = ['USD', 'EUR', 'GBP', 'AUD', 'NZD', 'JPY', 'CHF', 'CAD', 'MXN', 'ZAR'];
  data: any;

  showPositiveSwapsOnly: boolean = false;
  currentSymbol: string = 'USD'
  symbolFullName: string= ''

  constructor(private swapBackendService: SwapBackendService, private cotReportService: CotReportService) {

  }

  ngOnInit(): void {
    this.swapBackendService.dataSource.subscribe(data => this.data = data)
    this.cotReportService.symbolFullName.subscribe((fullname) => {
      this.symbolFullName = fullname;
    });
  }

  handleChange(theSymbol: string) {
    this.currentSymbol = theSymbol
    this.swapBackendService.updateSwapData(theSymbol, this.showPositiveSwapsOnly.toString());
    console.log(this.data.length);
  }

  toggle() {
    this.showPositiveSwapsOnly = !this.showPositiveSwapsOnly;
    console.log("positive swap only: " + this.showPositiveSwapsOnly)
    this.handleChange(this.currentSymbol);
  }



}
