import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { LiveFeedService } from 'src/app/services/live-feed.service';
import { Subscription } from 'rxjs';


@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.scss']
})
export class NewsComponent implements OnInit {

  tweets : any[] = [];
  sub!: Subscription;

  constructor(public service: LiveFeedService, private cd: ChangeDetectorRef) {}

	ngOnInit() {
    this.loadTweets()
	}

  loadTweets() {
    console.log("initialising connection to live twitter feed.")
    this.sub = this.service.getTweets()
    .subscribe( data => {
      if(data.id === "" || data.id.length <= 0 ) {
        // remove empty messages
      } else {
        this.tweets.push(data);
        this.tweets.reverse();
        this.cd.detectChanges();
      }
    });
  }

}

