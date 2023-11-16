import { Component, OnInit } from '@angular/core';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
//Possible ideas:
//Use localstorage to store a json web token.
export class AppComponent{
  title : string = "Arts Roc";
  constructor(){}
}
