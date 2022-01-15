import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CotTableComponent } from './components/cot-table/cot-table.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { MainContentComponent } from './components/main-content/main-content.component';
import { SideBarComponent } from './components/side-bar/side-bar.component';
import { SideNavService } from 'src/app/services/side-bar.service';
import { HttpClientModule } from '@angular/common/http';
import { CotReportComponent } from './components/cot-report/cot-report.component';
import { FormsModule } from '@angular/forms';
import { CotReportService } from './services/cot-report-backend.service';
import { DarkModeService } from './services/dark-mode.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { GoogleChartsModule } from 'angular-google-charts';
import { CountUpModule } from 'ngx-countup';
import { ReportChartsComponent } from './components/report-charts/report-charts.component';
import { Chart } from "chart.js";
import { MobileSidebarComponent } from './components/mobile-sidebar/mobile-sidebar.component';

@NgModule({
  declarations: [
    AppComponent,
    CotTableComponent,
    NavbarComponent,
    MainContentComponent,
    SideBarComponent,
    CotReportComponent,
    ReportChartsComponent,
    MobileSidebarComponent    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,    
    GoogleChartsModule,
    CountUpModule    
  ],
  providers: [CotReportService, SideNavService, DarkModeService],
  bootstrap: [AppComponent]
})
export class AppModule { }
