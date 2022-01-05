import { SideNavService } from './../side-bar/side-bar.service';
import { SideBarComponent } from './../side-bar/side-bar.component';
import { Component, Input, OnInit } from '@angular/core';
import { DarkModeService } from 'src/app/services/dark-mode.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  constructor(private darkMode: DarkModeService, private sidebar: SideNavService) {}

  isDarkModeEnabled: any;
  ngOnInit(): void {
    this.darkMode.isDarkMode.subscribe(isDarkMode => this.isDarkModeEnabled = isDarkMode)     
  }

  clickMenu() {
    console.log("this.navbarComponent.togglemenu(): " + this.sidebar.toggle());
    return this.sidebar.toggle();
  }

  toggleDarkMode() {   
    console.log("dark mode: " + this.isDarkModeEnabled); 
    this.darkMode.toggleDarkMode();
  }

}
