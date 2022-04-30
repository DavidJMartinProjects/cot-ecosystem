import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { LiveFeedService } from 'src/app/services/live-feed.service';
import { Tweet } from './Tweet';
import { Subscription } from 'rxjs';


@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.scss']
})
export class NewsComponent implements OnInit {

  tweets : any[] = [];

  sub!: Subscription;

  constructor(public service: LiveFeedService, private cd: ChangeDetectorRef) {
  }

	ngOnInit() {
    this.search()
	}

  search() {
    console.log("calling api")
    this.sub = this.service.getTweets()
    .subscribe( data => {
      this.tweets.push(data);
      console.log('data', data);
      this.cd.detectChanges();
    });
  }

}

