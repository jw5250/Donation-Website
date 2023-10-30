import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms'; // <-- NgModel lives here
import { AppComponent } from './app.component';
import { MessagesComponent } from './messages/messages.component';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { HelperScreenComponent } from './accountSystemFrontend/helper-screen/helper-screen.component';
import { SigninLoginScreenComponent } from './accountSystemFrontend/signin-login-screen/signin-login-screen.component';
import { CupboardComponent } from './cupboard/cupboard.component';

//For testing the database.
import { HttpClientInMemoryWebApiModule } from 'angular-in-memory-web-api';
import { UserDatabaseTestService } from './user-database-test.service';
import { HelperChoiceInterfaceComponent } from './helper-choice-interface/helper-choice-interface.component';



@NgModule({
  declarations: [
    AppComponent,
    MessagesComponent,
    HelperScreenComponent,
    SigninLoginScreenComponent,
    CupboardComponent,
    HelperChoiceInterfaceComponent

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
