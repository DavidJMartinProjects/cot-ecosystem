import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { SideBarComponent } from './components/side-bar/side-bar.component';
import { MainContentComponent } from './components/main-content/main-content.component';
import { MainSideMenuComponent } from './components/main-side-menu/main-side-menu.component';
import { CotReportComponent } from './components/cot-report/cot-report.component';
import { GoogleChartsModule } from 'angular-google-charts';
import { CountUpModule } from 'ngx-countup';
import { ReportChartsComponent } from './components/report-charts/report-charts.component';
import { ReportTableComponent } from './components/report-table/report-table.component';
import { CotReportService } from 'src/services/cot-report-backend.service';
import { SideNavService } from 'src/services/side-bar.service';
import { DarkModeService } from 'src/services/dark-mode.service';

@NgModule({
  declarations: [
    AppComponent,
    SideBarComponent,
    MainContentComponent,
    MainSideMenuComponent,
    CotReportComponent,
    ReportChartsComponent,
    ReportTableComponent    
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
