import { Component, Input, OnInit } from '@angular/core';
import { SideNavService } from 'src/app/services/side-bar.service';
import { NavbarComponent } from '../navbar/navbar.component';

@Component({
  selector: 'app-main-content',
  templateUrl: './main-content.component.html',
  styleUrls: ['./main-content.component.scss']
})
export class MainContentComponent implements OnInit {
  isSideMenuEnabled: boolean = true;
  
  ngOnInit(): void {
    this.sideNavService.isMenuShowing.subscribe(isMenuEnabled => this.isSideMenuEnabled = isMenuEnabled);
  }

  constructor(private sideNavService: SideNavService) { }

  toggle() {
    // console.log("this.navbarComponent.togglemenu(): " + this.sideNavService.toggle());
    return this.sideNavService.toggle();
  } 
}
