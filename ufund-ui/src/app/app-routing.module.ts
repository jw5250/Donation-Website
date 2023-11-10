import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HelperChoiceInterfaceComponent } from './helperInterface/helper-choice-interface/helper-choice-interface.component';
import { HelperDonationRewardsInterfaceComponent } from './helperInterface/helper-donation-rewards-interface/helper-donation-rewards-interface.component';
import { FundingBasketComponent } from './fundingBasket/fundingBasket.component';
import { CheckoutPageComponent } from './helperInterface/checkout-page/checkout-page.component';
import { ManagerDonationRewardsInterfaceComponent } from './managerInterface/manager-donation-rewards-interface/manager-donation-rewards-interface.component';
import { SigninLoginScreenComponent } from './accountSystemFrontend/signin-login-screen/signin-login-screen.component';
import { CupboardComponent } from './managerInterface/cupboard/cupboard.component';
import { EventComponent } from './event/event.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path:'login', component: SigninLoginScreenComponent },
  { path: 'cupboard', component: CupboardComponent },
  { path: 'cupboard/donations', component: ManagerDonationRewardsInterfaceComponent },
  //":x" stands for variable x
  { path: 'user/:name/choices', component: HelperChoiceInterfaceComponent},
  { path: 'user/:name/reward', component: HelperDonationRewardsInterfaceComponent},
  { path: 'user/:name/basket', component: FundingBasketComponent },
  { path: 'checkout', component: CheckoutPageComponent },
  { path: 'events', component: EventComponent}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
