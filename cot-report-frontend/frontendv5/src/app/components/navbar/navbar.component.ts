import { SideNavService } from 'src/app/services/side-bar.service';
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
  isSideMenuEnabled:any;
  ngOnInit(): void {
    this.darkMode.isDarkMode.subscribe(isDarkMode => this.isDarkModeEnabled = isDarkMode)  
    this.sidebar.isMenuShowing.subscribe(isMenuEnabled => this.isSideMenuEnabled = isMenuEnabled);
  }

  toggleSideMenu() {  
    this.sidebar.toggle();
    console.log("side menu enabled: " + this.isSideMenuEnabled);    
  }

  toggleDarkMode() {   
    this.darkMode.toggleDarkMode();
    console.log("dark mode enabled: " + this.isDarkModeEnabled); 
  }

}
