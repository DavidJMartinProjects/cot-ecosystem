import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CotTableComponent } from './components/cot-table/cot-table.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { MainContentComponent } from './components/main-content/main-content.component';
import { SideBarComponent } from './components/side-bar/side-bar.component';

import { HttpClientModule } from '@angular/common/http';
import { CotReportComponent } from './components/cot-report/cot-report.component';
import { FormsModule } from '@angular/forms';
import { CotReportService } from './services/cotReportService.service';
import { DarkModeService } from './services/dark-mode.service';
import { NgxChartsModule } from '@swimlane/ngx-charts';
import { SideNavService } from './services/side-bar.service';
import { GoogleChartsModule } from 'angular-google-charts';
import { ReportChartsComponent } from './components/report-charts/report-charts.component';
import { CountUpModule } from 'ngx-countup';


@NgModule({
  declarations: [
    AppComponent,
    CotTableComponent,
    NavbarComponent,
    MainContentComponent,
    SideBarComponent,
    CotReportComponent,
    ReportChartsComponent   
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgxChartsModule,
    GoogleChartsModule,
    CountUpModule
  ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ],
  providers: [CotReportService, SideNavService, DarkModeService],
  bootstrap: [AppComponent]
})
export class AppModule { }
