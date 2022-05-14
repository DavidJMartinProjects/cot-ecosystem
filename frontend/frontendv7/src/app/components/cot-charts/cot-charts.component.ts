import { Component, OnInit, ViewChild } from '@angular/core';
// import { ChartType } from 'angular-google-charts';
import { CotReportService } from 'src/app/services/cot-report-backend.service';

// import Chart from 'chart.js/auto'

@Component({
  selector: 'app-cot-charts',
  templateUrl: './cot-charts.component.html',
  styleUrls: ['./cot-charts.component.scss']
})
export class CotChartsComponent implements OnInit {

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
    // this.pieChart = new Chart(this.chartRef.nativeElement, {
    //   type: 'pie',
    //   data: {
    //     datasets: [{
    //       backgroundColor: ['green', 'red'],
    //       label: 'Interesting Data',
    //       data: [this.data[0].longPositions, this.data[0].shortPositions]
    //     }]
    //   },
    //   options: {
    //     maintainAspectRatio: true,
    //     responsive: true,
    //     scales: {
    //     }
    //   }
    // });
    // this.pieChart.update();
  }

  ngAfterViewInit(): void {
    this.buildChart();
  }

}
