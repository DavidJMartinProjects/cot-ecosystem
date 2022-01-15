import { Component, OnInit } from '@angular/core';
import { ChartType } from 'angular-google-charts';
import { CotReportService } from 'src/app/services/cot-report-backend.service';


@Component({
  selector: 'app-cot-table',
  templateUrl: './cot-table.component.html',
  styleUrls: ['./cot-table.component.scss']
})
export class CotTableComponent implements OnInit {

  data: any;

  constructor(private cotReportService: CotReportService) {}

  ngOnInit(): void {
    this.cotReportService.dataSource.subscribe(data => this.data = data)
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

}
