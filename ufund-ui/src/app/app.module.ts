import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms'; // <-- NgModel lives here
import { AppComponent } from './app.component';
import { NeedsComponent } from './needs/needs.component';
import { NeedDetailComponent } from './need-detail/need-detail.component';
import { MessagesComponent } from './messages/messages.component';
import { AppRoutingModule } from './app-routing.module';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HttpClientModule } from '@angular/common/http';
import { NeedSearchComponent } from './need-search/need-search.component';
import { HelperScreenComponent } from './accountSystemFrontend/helper-screen/helper-screen.component';
import { ManagerScreenComponent } from './accountSystemFrontend/manager-screen/manager-screen.component';
import { SigninLoginScreenComponent } from './accountSystemFrontend/signin-login-screen/signin-login-screen.component';


//For testing the database.
import { HttpClientInMemoryWebApiModule } from 'angular-in-memory-web-api';
import { UserDatabaseTestService } from './user-database-test.service';

@NgModule({
  declarations: [
    AppComponent,
    NeedsComponent,
    NeedDetailComponent,
    MessagesComponent,
    DashboardComponent,
    NeedSearchComponent,
    HelperScreenComponent,
    ManagerScreenComponent,
    SigninLoginScreenComponent
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
