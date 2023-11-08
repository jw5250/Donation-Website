import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { FundingBasketComponent } from './fundingBasket/fundingBasket.component';
import { CheckoutPageComponent } from './checkout-page/checkout-page.component';
import { HelperScreenComponent } from './accountSystemFrontend/helper-screen/helper-screen.component';
import { SigninLoginScreenComponent } from './accountSystemFrontend/signin-login-screen/signin-login-screen.component';
import { CupboardComponent } from './cupboard/cupboard.component';

const routes: Routes = [
  { path: 'cupboard', component: CupboardComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  //":x" stands for variable x
  { path: 'user/:name', component: HelperScreenComponent},
  { path: 'user/:name/basket', component: FundingBasketComponent },
  { path: 'checkout', component: CheckoutPageComponent },
  { path:'login', component: SigninLoginScreenComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
