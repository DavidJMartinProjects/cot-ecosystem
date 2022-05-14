import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CalendarComponent } from './components/calendar/calendar.component';
import { CotChartsComponent } from './components/cot-charts/cot-charts.component';
import { CotReportComponent } from './components/cot-report/cot-report.component';
import { CotTableComponent } from './components/cot-table/cot-table.component';
import { NewsComponent } from './components/news/news.component';
import { SideBarComponent } from './components/side-bar/side-bar.component';
import { SwapsComponent } from './components/swaps/swaps.component';
import { CotReportService } from './services/cot-report-backend.service';
import { DarkModeService } from './services/dark-mode.service';
import { LiveFeedService } from './services/live-feed.service';
import { SideNavService } from './services/side-bar.service';
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
    FormsModule
  ],
  providers: [CotReportService, SwapBackendService, SideNavService, DarkModeService, LiveFeedService,],
  bootstrap: [AppComponent]
})
export class AppModule { }
