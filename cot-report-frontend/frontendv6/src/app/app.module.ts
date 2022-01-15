import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CotReportService } from './services/cot-report-backend.service';
import { DarkModeService } from './services/dark-mode.service';
import { SideNavService } from './services/side-bar.service';
import { SideBarComponent } from './components/side-bar/side-bar.component';
import { CotReportComponent } from './components/cot-report/cot-report.component';
import { CotChartsComponent } from './components/cot-charts/cot-charts.component';
import { CotTableComponent } from './components/cot-table/cot-table.component';
import { GoogleChartsModule } from 'angular-google-charts';
import { Chart } from "chart.js";
import { CountUpModule } from 'ngx-countup';

@NgModule({
  declarations: [
    AppComponent,
    SideBarComponent,
    CotReportComponent,
    CotChartsComponent,
    CotTableComponent
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
