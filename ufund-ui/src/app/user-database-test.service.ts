import { Injectable } from '@angular/core';
import { InMemoryDbService } from 'angular-in-memory-web-api';
import { User } from './user';
@Injectable({
  providedIn: 'root'
})
export class UserDatabaseTestService implements InMemoryDbService {
  createDb(){
    const users = [
      { name: 'Dr. Nice', isManager: false},
      { name: 'Bombasto', isManager: false},
      { name: 'Celeritas', isManager: false},
      { name: 'Magneta', isManager: false},
      { name: 'RubberMan', isManager: false},
      { name: 'Dynama', isManager: false},
      { name: 'Dr. IQ', isManager: true},
      { name: 'Magma', isManager: false},
      { name: 'Tornado', isManager: false}
    ];
    return {users};
  }
  constructor() { }
}
