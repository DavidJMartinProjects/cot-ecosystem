import { Component, OnInit } from '@angular/core';
import { SideNavService } from 'src/app/services/side-bar.service';

@Component({
  selector: 'app-mobile-sidebar',
  templateUrl: './mobile-sidebar.component.html',
  styleUrls: ['./mobile-sidebar.component.scss']
})
export class MobileSidebarComponent implements OnInit {

  isSideMenuEnabled: boolean = true;
  
  ngOnInit(): void {
    this.sideNavService.isMenuShowing.subscribe(isMenuEnabled => this.isSideMenuEnabled = isMenuEnabled);
  }

  constructor(private sideNavService: SideNavService) { }
  
  toggle() {
    console.log("isSideMenuEnabled" + this.isSideMenuEnabled);
    return this.sideNavService.toggle();
  }

}
