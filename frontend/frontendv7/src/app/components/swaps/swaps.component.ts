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
  thesymbolFullName: string= 'United States Dollar'

  constructor(private swapBackendService: SwapBackendService, private cotReportService: CotReportService) {
  }

  ngOnInit(): void {
    this.swapBackendService.dataSource.subscribe(data => this.data = data)
    this.cotReportService.symbolFullName.subscribe(data =>
      this.thesymbolFullName = data
    );
    this.cotReportService.updateSymbolData(this.currentSymbol);
  }

  handleChange(theSymbol: string) {
    this.currentSymbol = theSymbol
    this.swapBackendService.updateSwapData(theSymbol, this.showPositiveSwapsOnly.toString());
    this.cotReportService.updateSymbolData(theSymbol);
    console.log(this.thesymbolFullName);
  }

  toggle() {
    this.showPositiveSwapsOnly = !this.showPositiveSwapsOnly;
    console.log("positive swap only: " + this.showPositiveSwapsOnly)
    this.handleChange(this.currentSymbol);
  }



}
