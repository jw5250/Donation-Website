import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { HelperChoiceInterfaceComponent } from './helperInterface/helper-choice-interface/helper-choice-interface.component';
import { HelperDonationRewardsInterfaceComponent } from './helperInterface/helper-donation-rewards-interface/helper-donation-rewards-interface.component';
import { CheckoutPageComponent } from './helperInterface/checkout-page/checkout-page.component';
import { ManagerDonationRewardsInterfaceComponent } from './managerInterface/manager-donation-rewards-interface/manager-donation-rewards-interface.component';
import { SigninLoginScreenComponent } from './accountSystemFrontend/signin-login-screen/signin-login-screen.component';
import { CupboardComponent } from './managerInterface/cupboard/cupboard.component';
import { EventComponent } from './event/event.component';
import { EventDisplayComponent } from './event-display/event-display.component'
import { MainBodyComponent } from './main-body/main-body.component';

const routes: Routes = [
  { path: 'user/:name', component: MainBodyComponent,
  children : [  { path: 'cupboard', component: CupboardComponent },
  { path: 'donationManagement', component: ManagerDonationRewardsInterfaceComponent },
  { path: 'choices', component: HelperChoiceInterfaceComponent },
  { path: 'reward', component: HelperDonationRewardsInterfaceComponent },
  { path: 'checkout', component: CheckoutPageComponent },
  { path: 'login', component: SigninLoginScreenComponent},
  { path: 'eventManagement', component: EventComponent },
  { path: 'eventDisplay', component: EventDisplayComponent}
  ]},
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: SigninLoginScreenComponent},
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
