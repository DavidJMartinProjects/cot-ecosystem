import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';
import { DataTablesModule } from 'angular-datatables';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatRadioModule } from '@angular/material/radio'
import { HttpClientModule } from '@angular/common/http';
import {MatCardModule} from '@angular/material/card';

import { AppComponent } from './app.component';
import { CotReportComponent } from './cot-report/cot-report.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgxChartsModule } from '@swimlane/ngx-charts';

const routes: Routes = [
  { path: 'cot-report', component: CotReportComponent }
]

@NgModule({
  declarations: [
    AppComponent,
    CotReportComponent
  ],
  imports: [
    DataTablesModule,
    MatRadioModule,    
    BrowserModule,     
    FormsModule,
    MatProgressSpinnerModule,  
    HttpClientModule, 
    BrowserAnimationsModule, 
    NgxChartsModule,
    MatCardModule,   
    RouterModule.forRoot(routes), BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
