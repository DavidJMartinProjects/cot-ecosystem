import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CotTableComponent } from './components/cot-table/cot-table.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { MainContentComponent } from './components/main-content/main-content.component';
import { SideBarComponent } from './components/side-bar/side-bar.component';
import { SideNavService } from './components/side-bar/side-bar.service';
import { HttpClientModule } from '@angular/common/http';
import { CotReportComponent } from './components/cot-report/cot-report.component';

@NgModule({
  declarations: [
    AppComponent,
    CotTableComponent,
    NavbarComponent,
    MainContentComponent,
    SideBarComponent,
    CotReportComponent    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [SideNavService, SideBarComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
