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
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatCardModule } from '@angular/material/card';
import { MatMenuModule } from '@angular/material/menu';
import { MatRadioModule } from '@angular/material/radio';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatInputModule } from '@angular/material/input';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSortModule } from '@angular/material/sort';
import { GoogleChartsModule } from 'angular-google-charts';
import { CountUpModule } from 'ngx-countup';

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
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,        
    MatGridListModule,
    MatCardModule,
    MatMenuModule,
    MatRadioModule,
    MatTableModule,
    FormsModule,
    HttpClientModule,    
    MatInputModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatProgressSpinnerModule,    
    GoogleChartsModule,
    CountUpModule
  ],
  providers: [CotReportService, SideNavService, DarkModeService],
  bootstrap: [AppComponent]
})
export class AppModule { }
