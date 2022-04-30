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

  constructor(private http: HttpClient) { }

  // /** GET tweets asynch feed */
  // getTweets(): Observable<Object> {
  //   console.log("GET: " + this.baseUrl)
  //   return this.http.get(`${this.baseUrl}`);
  // }
  getTweets(): Observable<any> {
    return new Observable<any>((observer) => {
      let url = this.baseUrl;

      let eventSource = new EventSource(url);
      eventSource.onmessage = (event) => {
        console.log('Received event: ', event);

        // let json = JSON.parse(event.data);

        // let tweet = JSON.parse(event.data);
        // observer.next(new Tweet(tweet['id'], tweet['createdAt'], tweet['text']));
        observer.next(event.data);

      };
      eventSource.onerror = (error) => {
        // readyState === 0 (closed) means the remote source closed the connection,
        // so we can safely treat it as a normal situation. Another way
        // of detecting the end of the stream is to insert a special element
        // in the stream of events, which the client can identify as the last one.
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
