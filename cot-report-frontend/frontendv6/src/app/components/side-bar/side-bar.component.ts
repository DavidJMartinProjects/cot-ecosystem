import { Component, OnInit } from '@angular/core';
import { SideNavService } from 'src/app/services/side-bar.service';

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.scss']
})
export class SideBarComponent implements OnInit {
  
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
