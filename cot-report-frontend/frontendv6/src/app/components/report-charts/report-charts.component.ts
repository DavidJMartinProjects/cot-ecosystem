import { Component, OnInit, ViewChild } from '@angular/core';
import { ChartType } from 'angular-google-charts';

import { Chart } from 'chart.js';
import { CotReportService } from 'src/services/cot-report-backend.service';


@Component({
  selector: 'app-report-charts',
  templateUrl: './report-charts.component.html',
  styleUrls: ['./report-charts.component.scss']
})
export class ReportChartsComponent implements OnInit {

  public pieChartData: any[] = []
  pieChart: any = [];

  @ViewChild('chart')
  private chartRef: any;
  public data: any[] = [];

  constructor(private cotReportService: CotReportService) { }

  ngOnInit(): void {
    this.cotReportService.dataSource.subscribe(data => {
      this.data = data
      this.pieChart.data.datasets.data = [this.data[0].longPositions, this.data[0].shortPositions] 
      this.pieChart.destroy(); 
      this.buildChart();  
    });
  }

  myCountUpOpts = {
    duration: .3
  }

  buildChart() {
    this.pieChart = new Chart(this.chartRef.nativeElement, {
      type: 'pie',
      data: {
        datasets: [{
          backgroundColor: ['rgba(255, 0, 0, 1)', 'rgba(255, 0, 0, 0.1)'],
          label: 'Interesting Data',
          data: [this.data[0].longPositions, this.data[0].shortPositions]          
        }]
      },
      options: {
        maintainAspectRatio: false,
        responsive: true,
        scales: {
        }
      }
    });    
    this.pieChart.update();
  }

  ngAfterViewInit(): void {
    this.buildChart();
  }  
}
