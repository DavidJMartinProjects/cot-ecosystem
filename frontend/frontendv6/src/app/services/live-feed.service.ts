import { Injectable, OnInit } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Tweet } from '../components/news/Tweet';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class LiveFeedService {

  private baseUrl = 'http://localhost:8080/tweets/queue/tweetQueue';
  // "/api/swaps"

  constructor(private http: HttpClient) { }

  // /** GET tweets asynch feed */
  getTweets(): Observable<Tweet> {
    return new Observable<Tweet>((observer) => {

      let eventSource = new EventSource(this.baseUrl);
      eventSource.onmessage = (event) => {
        let tweet = JSON.parse(event.data);
        observer.next(new Tweet(tweet['id'], tweet['createdAt'], tweet['text']));
        observer.next(event.data);
      };

      eventSource.onerror = (error) => {
        if(eventSource.readyState === 0) {
          console.log('The stream has been closed by the server.');
          eventSource.close();
          observer.complete();
        } else {
          observer.error('EventSource error: ' + error);
        }
      }

    });
  }

}
