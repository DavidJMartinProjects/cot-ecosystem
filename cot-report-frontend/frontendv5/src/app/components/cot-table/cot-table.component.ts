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

  
    titles = { id: '#',  type: 'type of food', calori: 'calories', tasty: 'tasty factor', price: 'average price', rarity: 'rarity', rating: 'average rating'};
    titles1 = [{ id: '#',  type: 'type of food', calori: 'calories', tasty: 'tasty factor', price: 'average price', rarity: 'rarity', rating: 'average rating'}];
    foods = [
      { id: 0,  type: 'Slice of Pizza', calori: '450', tasty: '95%', price: '$5.00', rarity: 'Common', rating: '8/10'},
			{ id: 1,  type: 'Hamburger', calori: '350', tasty: '87%', price: '$3.50', rarity: 'Common', rating: '7.5/10'},
      { id: 2,  type: 'Slice of Pizza', calori: '450', tasty: '95%', price: '$5.00', rarity: 'Common', rating: '8/10'},
      { id: 3,  type: 'Hamburger', calori: '350', tasty: '87%', price: '$3.50', rarity: 'Common', rating: '7.5/10'},
      { id: 4,  type: 'Slice of Pizza', calori: '450', tasty: '95%', price: '$5.00', rarity: 'Common', rating: '8/10'},
      { id: 5,  type: 'Hamburger', calori: '350', tasty: '87%', price: '$3.50', rarity: 'Common', rating: '7.5/10'},
      { id: 6,  type: 'Slice of Pizza', calori: '450', tasty: '95%', price: '$5.00', rarity: 'Common', rating: '8/10'},
      { id: 7,  type: 'Hamburger', calori: '350', tasty: '87%', price: '$3.50', rarity: 'Common', rating: '7.5/10'},
      { id: 8,  type: 'Slice of Pizza', calori: '450', tasty: '95%', price: '$5.00', rarity: 'Common', rating: '8/10'},
      { id: 9,  type: 'Hamburger', calori: '350', tasty: '87%', price: '$3.50', rarity: 'Common', rating: '7.5/10'},
      { id: 10,  type: 'Slice of Pizza', calori: '450', tasty: '95%', price: '$5.00', rarity: 'Common', rating: '8/10'}
    ]
  

}
