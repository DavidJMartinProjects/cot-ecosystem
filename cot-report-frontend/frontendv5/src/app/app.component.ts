import { Component, OnInit } from '@angular/core';
import { DarkModeService } from './services/dark-mode.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {  

  constructor(private darkModeService: DarkModeService) {}

  isDarkModeEnabled: Boolean = false;  
  ngOnInit(): void {
    this.darkModeService.isDarkMode.subscribe(data => this.isDarkModeEnabled = data)
  }
  
}
