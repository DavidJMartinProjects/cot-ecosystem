import { Component, OnInit, ViewChild } from '@angular/core';
import {AfterViewInit, ElementRef} from '@angular/core';
import { CotReportService } from 'src/app/services/cot-report-backend.service';
import Chart from 'chart.js/auto';

@Component({
  selector: 'app-cot-report',
  templateUrl: './cot-report.component.html',
  styleUrls: ['./cot-report.component.scss']
})
export class CotReportComponent implements OnInit {

  @ViewChild('chart')
  private chartRef: any;
  private chart: any;
  private data: any[] = [];
  

  symbols: string[] = ['USD', 'EUR', 'GBP', 'AUD', 'NZD', 'JPY', 'CHF', 'CAD', 'MXN', 'ZAR', 'GOLD', 'BTC'];
  PieChart = [];
  pieChartData: any;

  constructor(private cotReportService: CotReportService) {  
    this.data = [{x: 1, y: 5}, {x: 2, y: 10}, {x: 3, y: 6}, {x: 4, y: 2}, {x: 4.1, y: 6}];   
    // this.pieChartData = new Chart('pieChart', {
    //   type: 'doughnut',
    //   data: {
    //     labels: ["Blue", "Green", "Pink"],
    //     datasets: [{
    //       label: 'Vote Now',
    //       data: [101, 102, 103],
    //       backgroundColor: [
    //         'rgba(40,23,244,0.9)',
    //         'rgba(192,255,0,0.9)',
    //         'rgba(239,23,240,0.9)',
    //       ]
    //     }]
    //   }
    // })    
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
          // data: this.data,
          data: this.pieChartData,
          // fill: false
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
