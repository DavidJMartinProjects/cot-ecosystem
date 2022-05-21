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

  // swaps_api_url: string = "/api/swaps?symbol=${symbol}&filterPositiveSwaps=${filterPositiveSwaps}";
  getSwapData(symbol: string, filterPositiveSwaps: string) {
    var swaps_api_url = `/api/swaps?symbol=${symbol}&filterPositiveSwaps=${filterPositiveSwaps}`;
    // var swaps_api_url = `http://localhost:8081/api/swaps?symbol=${symbol}&filterPositiveSwaps=${filterPositiveSwaps}`;
    this.http
      .get<any>(swaps_api_url)
      .subscribe((response: any[]) => {
        console.log('GET: ' + swaps_api_url);
        this.data = response
        this.dataSource.next(this.data);
      }, (error: any) => {
        console.log(error);
      })
  }

}
