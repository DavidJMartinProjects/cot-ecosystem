import { Component, OnInit } from '@angular/core';
import { ChartType } from 'angular-google-charts';
import { CotReportService } from 'src/app/services/cotReportService.service';

@Component({
  selector: 'app-report-charts',
  templateUrl: './report-charts.component.html',
  styleUrls: ['./report-charts.component.scss']
})
export class ReportChartsComponent implements OnInit {

  pieChartData: any;
  data: any;

  constructor(private cotReportService: CotReportService) {}

  ngOnInit(): void {
    this.cotReportService.dataSource.subscribe(data => this.pieChartData = this.buildPieChartData(data)) 
    this.cotReportService.dataSource.subscribe(data => this.data = data) 
  }

  buildPieChartData(data: any) {
    return this.pieChartData = [
      [ "shorts", data[0].shortPositions ],
      [ "longs", data[0].longPositions ],
    ];
  }

  lightblue = 'lightblue';
  lightgreen = 'lightgreen';
  lightpink = 'lightpink';

  myType = ChartType.PieChart;
  width = 160;
  height = 160;
  options = {
    legend: { position: 'bottom' },      
    colors: ['#949FB1', 'green'],
    pieHole:0.8,
    animation: {      
      duration: 2000,
      easing: 'in',
      startup: true
   },
   pieSliceTextStyle: {
    color: 'white',    
  }
  };

  myCountUpOpts = {
    duration: .3
  }

}
