import { Component, OnInit, ViewChild } from '@angular/core';

import { Chart } from "chart.js";
import { CotReportService } from 'src/services/cot-report-backend.service';

@Component({
  selector: 'app-cot-report',
  templateUrl: './cot-report.component.html',
  styleUrls: ['./cot-report.component.scss']
})
export class CotReportComponent implements OnInit {


  @ViewChild('chart')
  private chartRef: any;
  private chart: any;
  
  symbols: string[] = ['USD', 'EUR', 'GBP', 'AUD', 'NZD', 'JPY', 'CHF', 'CAD', 'MXN', 'ZAR', 'GOLD', 'BTC'];
  PieChart = [];
  pieChartData: any;

  constructor(private cotReportService: CotReportService) {   
  }

  ngOnInit(): void {    
  }

  handleChange(theSymbol: any) {    
    this.cotReportService.updateSymbolData(theSymbol) ;
  }

  ngAfterViewInit(): void {
    this.chart = new Chart(this.chartRef.nativeElement, {
      type: 'pie',
      
      data: {
        
        datasets: [{
          backgroundColor: ['rgba(255, 0, 0, 1)','rgba(255, 0, 0, 0.1)'],
          label: 'Interesting Data',
          data: this.pieChartData,
        }]
      },
      options: {
        responsive: false,
        scales: {
        }
      }
    });
  }
}
