import { HttpClient } from '@angular/common/http';
import { temporaryAllocator } from '@angular/compiler/src/render3/view/util';
import { Injectable, OnInit } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable()
export class DarkModeService implements OnInit {
  
  public isDarkMode: BehaviorSubject<any> = new BehaviorSubject(false);
    
  constructor(private http : HttpClient) {          
  }
  
  ngOnInit(): void {        
  }

  showMenu: boolean = false;
  toggleDarkMode() {
    this.showMenu = !this.showMenu;
    return this.isDarkMode.next(this.showMenu);       
  }

}
