import { SideNavService } from './../side-bar/side-bar.service';
import { SideBarComponent } from './../side-bar/side-bar.component';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  constructor(private sidebar: SideNavService) { }

  ngOnInit(): void {
  }

  clickMenu() {
    console.log("this.navbarComponent.togglemenu(): " + this.sidebar.toggle());
    return this.sidebar.toggle();
  }

}
