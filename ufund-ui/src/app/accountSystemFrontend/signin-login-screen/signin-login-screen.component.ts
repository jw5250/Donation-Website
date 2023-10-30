import { Component, Input, Output, EventEmitter } from '@angular/core';
import { MessageService } from './../../message.service';
import { UserService } from '../../user.service';
import { User } from '../../user';
@Component({
  selector: 'app-signin-login-screen',
  templateUrl: './signin-login-screen.component.html',
  styleUrls: ['./signin-login-screen.component.css']
})

//Neither the signIn or logIn functions are working.
//Current problem: Can't access the databases.
export class SigninLoginScreenComponent {
  constructor(
    private userService: UserService,
    private messanger: MessageService
  ) {}
  //Used so this component can communicate with those that use it.
  @Output() person : EventEmitter<User> = new EventEmitter<User>();
  //The input for the sign in name
  @Input() signInName?:string = undefined;
  //The input for the log in name.
  @Input() logInName?:string = undefined;
  //The user's data.
  user? : User = undefined;

  signInErrorMessage : string = "";
  logInErrorMessage : string = "";

  //Create a new account in the database.
  signIn(): void {
    this.signInErrorMessage = "";
    if(this.signInName != undefined){
      //Prevent names with whitespace from existing because of how this stuff is interpreted.
      if(this.signInName.indexOf(" ") == -1){
        
        this.userService.addData({name : this.signInName, isManager : false, fundingBasket : []} as User)
        .subscribe();
      }else{
        this.signInErrorMessage = "No whitespace is allowed in names.";
      }
      this.signInName = undefined;
    }
  }
  //Access some account in the database.
  logIn(): void {
    this.logInErrorMessage = "";
    if (this.logInName != undefined) {
      this.userService.getData(this.logInName)
        .subscribe((user) => {
          this.user = user;
          this.person.emit(user);
          if(user != undefined){
            this.messanger.add("Found account:" + user.name);
          }else{
            this.messanger.add("Invalid account!");
            this.logInErrorMessage = "Account does not exist.";
          }
        });
    }
    this.logInName = undefined;
  }
  //Log out of the account
  logOut(): void{
    if(this.user == undefined){
      return;
    }
    this.userService.updateData(this.user).subscribe(()=>{
      this.person.emit(undefined);
      this.user = undefined;
    });
  }
}
