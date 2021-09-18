import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { CotReportComponent } from './cot-report/cot-report.component';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatRadioModule } from '@angular/material/radio'

const routes: Routes = [
  { path: 'cot-report', component: CotReportComponent }
]


@NgModule({
  declarations: [
    AppComponent,
    CotReportComponent
  ],
  imports: [
    MatRadioModule,    
    BrowserModule,     
    FormsModule,
    MatProgressSpinnerModule,  
    HttpClientModule,      
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
