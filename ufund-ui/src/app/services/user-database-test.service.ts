import { Injectable } from '@angular/core';
import { InMemoryDbService } from 'angular-in-memory-web-api';
import { User } from '../dataClasses/user';
@Injectable({
  providedIn: 'root'
})
export class UserDatabaseTestService implements InMemoryDbService {
  createDb(){
    const users = [
      {"name":"Bob", "isManager":false, "fundingBasket":[]}
    ];
    return {users};
  }
  constructor() { }
}
