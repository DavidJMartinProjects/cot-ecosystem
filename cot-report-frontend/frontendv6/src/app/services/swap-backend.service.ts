import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SwapBackendService {


  data: any[] = [];
  public dataSource = new BehaviorSubject<string[]>(new Array);

  constructor(private http : HttpClient) {
    this.updateSwapData('USD', 'false');
  }

  ngOnInit(): void {
  }

  updateSwapData(symbol: string, filterPositiveSwaps: string) {
    this.getSwapData(symbol, filterPositiveSwaps);
  }

  swaps_api_url: string = "/api/swaps?symbol=${symbol}&filterPositiveSwaps=${filterPositiveSwaps}";
  getSwapData(symbol: string, filterPositiveSwaps: string) {
    this.http
      .get<any>(this.swaps_api_url)
      .subscribe((response) => {
        console.log('GET: ' + this.swaps_api_url);        
        this.dataSource.next(this.data);
      }, error => {
        console.log(error);
      })
  }

}
