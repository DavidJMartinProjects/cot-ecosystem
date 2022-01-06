import { Component, OnInit } from '@angular/core';
import { CotReportService } from 'src/app/services/cotReportService.service';



@Component({
  selector: 'app-cot-table',
  templateUrl: './cot-table.component.html',
  styleUrls: ['./cot-table.component.scss']
})
export class CotTableComponent implements OnInit {
  
  data: any;
  pieChartData: any;
  // pieChartDataNew: any;


  constructor(private cotReportService: CotReportService) {}

  ngOnInit(): void {
    this.cotReportService.dataSource.subscribe(data => this.data = data)         
  }


}
