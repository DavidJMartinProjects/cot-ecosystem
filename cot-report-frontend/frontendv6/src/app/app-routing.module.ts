import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CotReportComponent } from './components/cot-report/cot-report.component';

const routes: Routes = [];

@NgModule({
  imports: [RouterModule.forRoot([    
    { path: 'home', component: CotReportComponent },
    // { path: 'swaps', component: CatalogViewComponent },
    // { path: '**', redirectTo: 'login' }
  ])],
  exports: [RouterModule]
})
export class AppRoutingModule { }
