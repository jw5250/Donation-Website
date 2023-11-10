import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { FundingBasketComponent } from './fundingBasket/fundingBasket.component';
import { CheckoutPageComponent } from './helperInterface/checkout-page/checkout-page.component';

import { SigninLoginScreenComponent } from './accountSystemFrontend/signin-login-screen/signin-login-screen.component';
import { CupboardComponent } from './managerInterface/cupboard/cupboard.component';
import { EventComponent } from './event/event.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path:'login', component: SigninLoginScreenComponent },
  { path: 'cupboard', component: CupboardComponent },
  //":x" stands for variable x
  { path: 'user/:name/basket', component: FundingBasketComponent },
  { path: 'checkout', component: CheckoutPageComponent },
  //{ path: 'events', component: EventComponent}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
