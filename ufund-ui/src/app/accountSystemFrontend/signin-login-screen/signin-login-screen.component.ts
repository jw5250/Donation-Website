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
  @Output() person : EventEmitter<User> = new EventEmitter<User>();
  @Input() signInName?:string = undefined;
  @Input() logInName?:string = undefined;
  user? : User = undefined;
  signIn(): void {
    if(this.signInName != undefined){
      this.userService.addData({name : this.signInName, isManager : false} as User)
        .subscribe();
      this.signInName = undefined;
    }
  }
  logIn(): void {
    if (this.logInName != undefined) {
      this.userService.getData(this.logInName)
        .subscribe((user) => {
          this.user = user;
          this.person.emit(user);
          if(user != undefined){
            this.messanger.add("Found account:" + user.name);
          }else{
            this.messanger.add("Invalid account!");
          }
        });
    }
    this.logInName = undefined;
  }
  //Screen will now render nothing but the PUT method is broken.
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
