import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-report-table',
  templateUrl: './report-table.component.html',
  styleUrls: ['./report-table.component.css']
})
export class ReportTableComponent implements OnInit {

  selectedSymbol: string = 'USD';
  symbols: string[] = ['USD', 'EUR', 'GBP', 'AUD', 'JPY', 'CHF', 'CAD', 'NAD', 'MXN', 'ZAR', 'BTC'];
  
  constructor() { }

  ngOnInit(): void {
  }

  handleChange(theSymbol: string) {
    // this.getSymbolData(theSymbol);
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

  data = [
    {
    id: 9639,
    instrument: "USD",
    reportDate: "16-Nov-2021",
    longPositions: 47959,
    shortPositions: 13051,
    changeLong: "1438.0",
    changeShort: "1978.0",
    percentageLong: "80.8",
    percentageShort: "22.0",
    percentageLongChange: "0.2",
    percentageShortChange: "2.8",
    netPositions: 34908,
    netPositionsChange: -540
    },
    {
    id: 9640,
    instrument: "USD",
    reportDate: "09-Nov-2021",
    longPositions: 46521,
    shortPositions: 11073,
    changeLong: "1126.0",
    changeShort: "660.0",
    percentageLong: "80.6",
    percentageShort: "19.2",
    percentageLongChange: "2.3",
    percentageShortChange: "1.2",
    netPositions: 35448,
    netPositionsChange: 466
    },
    {
    id: 9641,
    instrument: "USD",
    reportDate: "02-Nov-2021",
    longPositions: 45395,
    shortPositions: 10413,
    changeLong: "206.0",
    changeShort: "-319.0",
    percentageLong: "78.3",
    percentageShort: "18.0",
    percentageLongChange: "1.7",
    percentageShortChange: "-0.2",
    netPositions: 34982,
    netPositionsChange: 525
    },
    {
    id: 9642,
    instrument: "USD",
    reportDate: "26-Oct-2021",
    longPositions: 45189,
    shortPositions: 10732,
    changeLong: "-2379.0",
    changeShort: "-902.0",
    percentageLong: "76.6",
    percentageShort: "18.2",
    percentageLongChange: "-1.7",
    percentageShortChange: "-1",
    netPositions: 34457,
    netPositionsChange: -1477
    },
    {
    id: 9643,
    instrument: "USD",
    reportDate: "19-Oct-2021",
    longPositions: 47568,
    shortPositions: 11634,
    changeLong: "-1826.0",
    changeShort: "-2698.0",
    percentageLong: "78.3",
    percentageShort: "19.2",
    percentageLongChange: "-0.3",
    percentageShortChange: "-3.6",
    netPositions: 35934,
    netPositionsChange: 872
    },
    {
    id: 9644,
    instrument: "USD",
    reportDate: "12-Oct-2021",
    longPositions: 49394,
    shortPositions: 14332,
    changeLong: "4679.0",
    changeShort: "1643.0",
    percentageLong: "78.6",
    percentageShort: "22.8",
    percentageLongChange: "0.4",
    percentageShortChange: "0.6",
    netPositions: 35062,
    netPositionsChange: 3036
    },
    {
    id: 9645,
    instrument: "USD",
    reportDate: "05-Oct-2021",
    longPositions: 44715,
    shortPositions: 12689,
    changeLong: "-339.0",
    changeShort: "-5904.0",
    percentageLong: "78.2",
    percentageShort: "22.2",
    percentageLongChange: "-3.2",
    percentageShortChange: "-11.4",
    netPositions: 32026,
    netPositionsChange: 5565
    },
    {
    id: 9646,
    instrument: "USD",
    reportDate: "28-Sep-2021",
    longPositions: 45054,
    shortPositions: 18593,
    changeLong: "8171.0",
    changeShort: "6810.0",
    percentageLong: "81.4",
    percentageShort: "33.6",
    percentageLongChange: "1.1",
    percentageShortChange: "7.9",
    netPositions: 26461,
    netPositionsChange: 1361
    }
  ]


}
