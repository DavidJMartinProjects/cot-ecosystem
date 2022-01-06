import { Component, OnInit } from '@angular/core';
import { ChartType } from 'angular-google-charts';
import { CotReportService } from 'src/app/services/cot-report-backend.service';


@Component({
  selector: 'app-cot-table',
  templateUrl: './cot-table.component.html',
  styleUrls: ['./cot-table.component.scss']
})
export class CotTableComponent implements OnInit {
  
  pieChartData: any;
  data: any;

  constructor(private cotReportService: CotReportService) {}

  ngOnInit(): void {
    this.cotReportService.dataSource.subscribe(data => this.data = data)
    this.cotReportService.dataSource.subscribe(data => this.pieChartData = this.buildPieChartData(data)) 
  }

  buildPieChartData(data: any) {
    return this.pieChartData = [
      [ "shorts", data[0].shortPositions ],
      [ "longs", data[0].longPositions ],
    ];
  }

  displayedColumns =
  [
    'reportDate',
    'longPositions',
    'shortPositions',
    'percentageLong',
    'percentageShort',
    'netPositions'
  ];

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
