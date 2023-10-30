import { Component, OnInit } from '@angular/core';
import { User } from './user';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
//Possible ideas:
//Use localstorage to store a json web token.
export class AppComponent{
  title : string = "Arts Roc";
  userData? : User;
  appComponent(){
  }
  getData(person:User){
    this.userData = person;
  }
  //Code below should be refactored accordingly.
  getUserName(){
    if(this.userData === undefined){
      return undefined;
    }else{
      return this.userData.name;
    }
  }
  getUser(){
    return this.userData;
  }
  isHelper(){
    return ( (this.userData !== undefined) && (this.userData.isManager === false));
  }
  isManager(){
    return ( (this.userData !== undefined) && (this.userData.isManager === true));
  }
  noAccount(){
    return this.userData === undefined;
  }
}
