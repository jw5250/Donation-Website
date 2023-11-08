import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SigninLoginScreenComponent } from './accountSystemFrontend/signin-login-screen/signin-login-screen.component';
import { CupboardComponent } from './managerInterface/cupboard/cupboard.component';

const routes: Routes = [
  { path: 'cupboard', component: CupboardComponent },
  //{ path: 'dashboard', component: DashboardComponent },
  /*{ path: '', redirectTo: '/dashboard', pathMatch: 'full' },*/
  //":x" stands for variable x
  //{ path: 'user/:id', component: NeedDetailComponent },
  
  {path:'login', component: SigninLoginScreenComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
