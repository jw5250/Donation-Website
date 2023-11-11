import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms'; // <-- NgModel lives here
import { AppComponent } from './app.component';
import { MessagesComponent } from './other/messages/messages.component';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { SigninLoginScreenComponent } from './accountSystemFrontend/signin-login-screen/signin-login-screen.component';
import { CupboardComponent } from './managerInterface/cupboard/cupboard.component';
import { EventComponent } from './event/event.component';

//For testing the database.
import { HttpClientInMemoryWebApiModule } from 'angular-in-memory-web-api';
import { UserDatabaseTestService } from './services/user-database-test.service';
import { HelperChoiceInterfaceComponent } from './helperInterface/helper-choice-interface/helper-choice-interface.component';
import { CheckoutPageComponent } from './helperInterface/checkout-page/checkout-page.component';
import { HelperDonationRewardsInterfaceComponent } from './helperInterface/helper-donation-rewards-interface/helper-donation-rewards-interface.component';
import { ManagerDonationRewardsInterfaceComponent } from './managerInterface/manager-donation-rewards-interface/manager-donation-rewards-interface.component';



@NgModule({
  declarations: [
    AppComponent,
    MessagesComponent,
    SigninLoginScreenComponent,
    CupboardComponent,
    HelperChoiceInterfaceComponent,
    CheckoutPageComponent,
    HelperDonationRewardsInterfaceComponent,
    ManagerDonationRewardsInterfaceComponent,
    EventComponent

  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    /*
    HttpClientInMemoryWebApiModule will now simulate a server,
    preventing the actual server from getting actual requests.
    */
    /*HttpClientInMemoryWebApiModule.forRoot(
    UserDatabaseTestService, { dataEncapsulation: false })*/
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
