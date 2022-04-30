import { LiveFeedService } from 'src/app/services/live-feed.service';
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
import { SwapsComponent } from './components/swaps/swaps.component';
import { CalendarComponent } from './components/calendar/calendar.component';
import { NewsComponent } from './components/news/news.component';
import { SwapBackendService } from './services/swap-backend.service';

@NgModule({
  declarations: [
    AppComponent,
    SideBarComponent,
    CotReportComponent,
    CotChartsComponent,
    CotTableComponent,
    SwapsComponent,
    CalendarComponent,
    NewsComponent
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
  providers: [CotReportService, SwapBackendService, SideNavService, DarkModeService, LiveFeedService],
  bootstrap: [AppComponent]
})
export class AppModule { }
