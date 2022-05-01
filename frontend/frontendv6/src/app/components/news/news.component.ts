import { ChangeDetectorRef, Component, OnInit, OnDestroy } from '@angular/core';
import { LiveFeedService } from 'src/app/services/live-feed.service';
import { Subscription } from 'rxjs';


@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.scss']
})
export class NewsComponent implements OnInit, OnDestroy {

  tweets : any[] = [];
  static sub: Subscription;

  constructor(public service: LiveFeedService, private cd: ChangeDetectorRef) {}

	ngOnInit() {
    console.log("subscribing to tweet")
    this.loadTweets()
	}

  loadTweets() {
    console.log("initialising connection to live twitter feed.")
    NewsComponent.sub = this.service.getTweets()
    .subscribe( data => {
      // if(data.id === "" || data.id.length <= 0 ) {
      //   // remove empty messages
      // } else {
        this.tweets.push(data);
        this.tweets.reverse();
        this.cd.detectChanges();
      // }
    });
  }

  ngOnDestroy(): void {
    console.log("closing event stream.")
    NewsComponent.sub.unsubscribe();
  }

}

