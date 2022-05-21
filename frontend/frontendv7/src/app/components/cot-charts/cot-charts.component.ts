import { Component, OnInit, ViewChild } from '@angular/core';
import { CotReportService } from 'src/app/services/cot-report-backend.service';
import 'tw-elements';

import Chart from 'chart.js/auto'

@Component({
  selector: 'app-cot-charts',
  templateUrl: './cot-charts.component.html',
  styleUrls: ['./cot-charts.component.scss']
})
export class CotChartsComponent implements OnInit {

  public pieChartData: any[] = []
  public pieChart: any = [];
  public lineChart: any = []

  @ViewChild('chart')
  public chartRef: any;
  public data: any[] = [];

  @ViewChild('chartLine')
  public lineChartRef: any;


  constructor(private cotReportService: CotReportService) { }

  ngOnInit(): void {
    this.cotReportService.dataSource.subscribe(data => {
      this.data = data
      this.pieChart.data.datasets.data = [this.data[0].longPositions, this.data[0].shortPositions]
      this.pieChart.destroy();
      this.lineChart.destroy();
      this.buildChart();
    });
  }

  myCountUpOpts = {
    duration: .3
  }

  public buildChart() {
    this.pieChart = new Chart(this.chartRef.nativeElement, {
      type: 'pie',
      data: {
        datasets: [{
          backgroundColor: ['green', 'red'],
          label: 'Interesting Data',
          data: [this.data[0].longPositions, this.data[0].shortPositions]
        }]
      },
      options: {
        maintainAspectRatio: true,
        responsive: true,
        scales: {
        }
      }
    });
    this.pieChart.update();

    const labels = ["January", "February", "March", "April", "May", "June"];
    this.lineChart = new Chart(this.lineChartRef.nativeElement, {
      type: 'line',
      data: {
        labels: labels,
        datasets: [{
          label: "My First dataset",
          backgroundColor: "bg-stone-800",
          borderColor: "white",
          data: [0, 10, 5, 2, 20, 30, 45],
        }]
      },
      options: {
        maintainAspectRatio: false,
        responsive: true,
        color: 'white',
        scales: {
          yAxes: {
              ticks: {
                  color: "white"
              },
          },
          xAxes: {
              ticks: {
                  color: "white"
              },
              suggestedMin: -10,
              suggestedMax: 200
          }

      },
      },

    });
    Chart.defaults.backgroundColor = 'inherit';
    this.lineChart.update();

  }

  ngAfterViewInit(): void {
    this.buildChart();
  }

  private lineChartOptions:any = {
    legend : {
        labels : {
          fontColor : '#ffffff'
        }
    }
};

}
