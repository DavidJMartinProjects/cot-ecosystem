import { Component, OnInit } from '@angular/core';
import { SideNavService } from 'src/app/services/side-bar.service';

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.scss']
})
export class SideBarComponent implements OnInit {

  constructor(private sideNavService: SideNavService) { }

  ngOnInit(): void {
  }

  
  toggle() {
    // console.log("this.navbarComponent.togglemenu(): " + this.sideNavService.toggle());
    return this.sideNavService.toggle();
  } 

}
