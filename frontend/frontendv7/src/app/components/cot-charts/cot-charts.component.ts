import { Component, OnInit, ViewChild } from '@angular/core';
import { CotReportService } from 'src/app/services/cot-report-backend.service';
import 'tw-elements';

import Chart from 'chart.js/auto';

@Component({
  selector: 'app-cot-charts',
  templateUrl: './cot-charts.component.html',
  styleUrls: ['./cot-charts.component.scss'],
})
export class CotChartsComponent implements OnInit {
  public pieChartData: any[] = [];
  public pieChart: any = [];

  public lineChartData: any[] = [];
  public lineChart: any = [];
  public longPositions: any = [];
  public shortPositions: any = [];

  public labels: any = [];

  @ViewChild('chart')
  public chartRef: any;
  public data: any[] = [];

  @ViewChild('chartLine')
  public lineChartRef: any;

  constructor(private cotReportService: CotReportService) {}

  ngOnInit(): void {
    this.cotReportService.dataSource.subscribe((data) => {
      this.data = data;
      this.pieChart.data.datasets.data = [
        this.data[0].longPositions,
        this.data[0].shortPositions,
      ];
      this.pieChart.destroy();

      this.lineChart.data.datasets.data = this.data;
      this.lineChart.destroy();
      this.buildChart();
    });
  }

  myCountUpOpts = {
    duration: 0.3,
  };

  public buildChart() {
    this.pieChart = new Chart(this.chartRef.nativeElement, {
      type: 'pie',
      data: {
        datasets: [
          {
            backgroundColor: ['green', 'red'],
            label: 'Interesting Data',
            data: [this.data[0].longPositions, this.data[0].shortPositions],
          },
        ],
      },
      options: {
        maintainAspectRatio: true,
        responsive: true,
        scales: {},
      },
    });
    this.pieChart.update();

    // calculate lineChart data
    this.longPositions = [];
    this.shortPositions = [];
    for (let i = 0; i < this.data.length; i++) {
      console.log ("Block statement execution no." + i);
      this.longPositions[i] = this.data[i].longPositions
      this.shortPositions[i] = this.data[i].shortPositions
    }
    this.longPositions.reverse()
    this.shortPositions.reverse()

    // calculate lineChart labels
    this.labels = [];
    for (let i = 0; i < this.data.length; i++) {
      console.log ("Block statement execution no." + i);
      this.labels[i] = this.data[i].reportDate
    }
    this.labels.reverse()

    this.lineChart = new Chart(this.lineChartRef.nativeElement, {
      type: 'line',
      data: {
        labels: this.labels,
        datasets: [
          {
            label: 'Buys',
            data: this.longPositions,
            backgroundColor: 'green',
            borderColor: 'green',
            fill: false,
            borderWidth: 2,
            borderCapStyle: 'round'
          },
          {
            label: 'Sells',
            data: this.shortPositions,
            backgroundColor: 'red',
            borderColor: 'red',
            fill: false,
            borderWidth: 2
          },
        ],
      },
      options: {
        maintainAspectRatio: false,
        responsive: true,
        color: 'white',
        interaction: {
          mode: 'index',
          intersect: false,
        },
        elements: {
          line: {
              tension: 0.4
          }
        },
        animations: {
          tension: {
            duration: 400,
            easing: 'easeInOutQuad',
            loop: true,
          },
        },
        scales: {
          y: {
            display: true,
            position: 'right',
            title: {
              color: 'blue'
            },
            grid: {
              drawBorder: false,
            },
            ticks: {
              color: 'white'
            },
          },
          x: {
            display: true,
            position: 'bottom',
            // grid line settings
            grid: {
              drawOnChartArea: false, // only want the grid lines for one axis to show up
            },
            ticks: {
              color: 'white'
            },
          },
        },
      },
    });
    this.lineChart.update();
  }

  ngAfterViewInit(): void {
    this.buildChart();
  }


}
