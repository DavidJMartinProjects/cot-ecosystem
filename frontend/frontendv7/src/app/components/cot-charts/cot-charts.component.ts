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
  public lineChartData: any[] = [];
  public pieChart: any = [];
  public lineChart: any = [];
  public longPositions: any = [];
  public shortPositions: any = [];

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

    this.longPositions = [];
    this.shortPositions = [];
    for (let i = 0; i < this.data.length; i++) {
      console.log ("Block statement execution no." + i);
      this.longPositions[i] = this.data[i].longPositions
      this.shortPositions[i] = this.data[i].shortPositions
    }

    const labels = ['January', 'February', 'March', 'April', 'May', 'June'];
    this.lineChart = new Chart(this.lineChartRef.nativeElement, {
      type: 'line',
      data: {
        labels: labels,
        datasets: [
          {
            backgroundColor: 'bg-stone-800',
            borderColor: 'white',
            label: 'Dataset 1',
            data: this.longPositions,
          },
          {
            backgroundColor: 'bg-stone-800',
            borderColor: 'red',
            label: 'Dataset 2',
            data: this.shortPositions,
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
        animations: {
          tension: {
            duration: 400,
            easing: 'easeInQuad',
            from: 1,
            to: 0,
            loop: false,
          },
        },
        scales: {
          yAxes: {
            type: 'linear',
            display: true,
            position: 'left',
          },
          xAxes: {
            type: 'linear',
            display: true,
            position: 'right',

            // grid line settings
            grid: {
              drawOnChartArea: false, // only want the grid lines for one axis to show up
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
