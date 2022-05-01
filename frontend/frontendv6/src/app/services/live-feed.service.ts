import { Injectable, OnDestroy, OnInit } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Tweet } from '../components/news/Tweet';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class LiveFeedService implements OnDestroy {

  private baseUrl = '/api/tweets/queue/tweetQueue';
  static eventSource: EventSource = new EventSource('/api/tweets/queue/tweetQueue');

  constructor(private http: HttpClient) {
    console.log("initialising connection to live twitter feed.")
  }

  ngOnDestroy(): void {
      if (LiveFeedService.eventSource) {
        console.log("Closing eventstream.")
        LiveFeedService.eventSource.close();
      }
  }

  // /** GET tweets asynch feed */
  getTweets(): Observable<Tweet> {
    return new Observable<Tweet>((observer) => {
      LiveFeedService.eventSource.onmessage = (event) => {
        let tweet = JSON.parse(event.data);
        observer.next(new Tweet(tweet['id'], tweet['createdAt'], tweet['text']));
        observer.next(event.data);
      };

      LiveFeedService.eventSource.onerror = (error) => {
        if(LiveFeedService.eventSource.readyState === 0) {
          console.log('The stream has been closed by the server.');
          LiveFeedService.eventSource.close();
          observer.complete();
        } else {
          observer.error('EventSource error: ' + error);
        }
      }

    });
  }

}
