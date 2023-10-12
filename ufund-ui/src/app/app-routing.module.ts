import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NeedsComponent } from './needs/needs.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HeroDetailComponent } from './hero-detail/hero-detail.component';

const routes: Routes = [
  { path: 'cupboard', component: NeedsComponent },
  //{ path: 'dashboard', component: DashboardComponent },
  //{ path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  //{ path: 'detail/:id', component: HeroDetailComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
