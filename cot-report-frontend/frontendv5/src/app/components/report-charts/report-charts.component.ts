import { Component, OnInit, ViewChild } from '@angular/core';
import { ChartType } from 'angular-google-charts';
import { CotReportService } from 'src/app/services/cot-report-backend.service';
import { Chart } from 'chart.js';

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
  private chart: any;
  public data: any[] = [];

  constructor(private cotReportService: CotReportService) { }

  ngOnInit(): void {
    this.cotReportService.dataSource.subscribe(data => this.data = data);
    this.cotReportService.dataSource.subscribe(data => {
      console.log("data updated.");
      this.pieChart.data.datasets.data = [this.data[0].longPositions, this.data[0].shortPositions] 

      this.pieChart.destroy(); 
      this.buildChart();  
    });
  }

  myCountUpOpts = {
    duration: .3
  }

  buildChart() {
    this.cotReportService.dataSource.subscribe(data => {
      console.log("data updated.");
      this.pieChart.data.datasets.data = [this.data[0].longPositions, this.data[0].shortPositions] 
      this.pieChart.update();     
    });

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
        responsive: false,
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
