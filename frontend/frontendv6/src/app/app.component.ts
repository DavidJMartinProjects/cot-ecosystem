import { Component, OnInit } from '@angular/core';
import { DarkModeService } from './services/dark-mode.service';
import { SideNavService } from './services/side-bar.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  
  title = 'apes together strong';
  isDarkModeEnabled: any;
  isSideMenuEnabled: any;

  constructor(private darkMode: DarkModeService, private sidebar: SideNavService) {    
  }
  
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
