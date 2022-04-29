import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CalendarComponent } from './components/calendar/calendar.component';
import { CotReportComponent } from './components/cot-report/cot-report.component';
import { NewsComponent } from './components/news/news.component';
import { SwapsComponent } from './components/swaps/swaps.component';

const routes: Routes = [];

@NgModule({
  imports: [RouterModule.forRoot([    
    { path: 'cot-report', component: CotReportComponent },
    { path: 'swaps', component: SwapsComponent },
    { path: 'calendar', component: CalendarComponent },
    { path: 'news', component: NewsComponent },
    { path: '**', redirectTo: 'logicot-report' }
  ])],
  exports: [RouterModule]
})
export class AppRoutingModule { }
