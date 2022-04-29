import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ReportTableComponent } from './component/report-table/report-table.component';

const routes: Routes = [
  { path: '', component: ReportTableComponent },
  { path: 'report-table', component: ReportTableComponent }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
