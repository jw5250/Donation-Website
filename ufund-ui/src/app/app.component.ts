import { Component, OnInit } from '@angular/core';
import { User } from './user';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
//Possible ideas:
//Use localstorage to store a json web token.
export class AppComponent implements OnInit{
  title : string = "Arts Roc";
  name? : string;
  userData? : User;
  appComponent(){
    console.log("Variables done");
  }
  ngOnInit(){//Called every user refresh
    console.log("Initialized!");
  }
  getData(person:User){
    this.userData = person;
    if(this.userData != undefined){
    }
  }
  //Code below should be refactored accordingly.
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
