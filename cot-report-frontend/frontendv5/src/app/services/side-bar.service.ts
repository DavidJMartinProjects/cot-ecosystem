import { Injectable } from "@angular/core";
import { BehaviorSubject } from "rxjs";

@Injectable()
export class SideNavService {

  constructor() { }

  public isMenuShowing: BehaviorSubject<any> = new BehaviorSubject(false);

  showMenu: boolean = false;
  public toggle() {
    this.showMenu = !this.showMenu;
    return this.isMenuShowing.next(this.showMenu);
  }

}
