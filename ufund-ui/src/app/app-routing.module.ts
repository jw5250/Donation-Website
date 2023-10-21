import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NeedsComponent } from './needs/needs.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { NeedDetailComponent } from './need-detail/need-detail.component';
import { SigninLoginScreenComponent } from './accountSystemFrontend/signin-login-screen/signin-login-screen.component';

const routes: Routes = [
  { path: 'cupboard', component: NeedsComponent },
  { path: 'dashboard', component: DashboardComponent },
  /*{ path: '', redirectTo: '/dashboard', pathMatch: 'full' },*/
  //":x" stands for variable x
  { path: 'user/:id', component: NeedDetailComponent },
  
  {path:'login', component: SigninLoginScreenComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
