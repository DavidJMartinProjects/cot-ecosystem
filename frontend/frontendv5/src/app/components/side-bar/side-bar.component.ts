import { SideNavService } from 'src/app/services/side-bar.service';
import { Component, Input, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.scss']
})
export class SideBarComponent implements OnInit {
  @ViewChild('sidenav') public sidenav?: SideBarComponent;


  constructor(private sideNavService: SideNavService) { }

  ngOnInit(): void {
  }

  toggle() {
    // console.log("this.navbarComponent.togglemenu(): " + this.sideNavService.toggle());
    return this.sideNavService.toggle();
  } 

}
