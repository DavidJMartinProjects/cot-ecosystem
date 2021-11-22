import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ToolbarComponent } from './component/toolbar/toolbar.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material.module';
import { SideNavComponent } from './component/side-nav/side-nav.component';
import { ReportTableComponent } from './component/report-table/report-table.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AppSidenavComponent } from './component/app-sidenav/app-sidenav.component';

@NgModule({
  declarations: [
    AppComponent,
    ToolbarComponent,
    SideNavComponent,    
    AppSidenavComponent,
    ReportTableComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,    
    MaterialModule,
    NgbModule     
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
