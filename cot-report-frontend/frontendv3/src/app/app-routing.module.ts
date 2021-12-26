import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NavComponent } from './nav/nav.component';
import { ReportComponent } from './report/report.component';

const routes: Routes = [
  { path: '', component: ReportComponent },
  { path: 'home', component: ReportComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
