//Handle Data service injections
//Repetitive code. Maaybe this can be cut down?
/*Can be turned into a single generic class with multiple subclasses with respective
links later.
*/
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { MessageService } from './message.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import { User } from './user'
@Injectable({
  providedIn: 'root'
})
export class UserService {

  private usersUrl = 'http://localhost:8080/users' ;// URL to web api

  /*'api/users'*/
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private messageService: MessageService)
    {}
  
  /** Log a DataService message with the MessageService */
  private log(message: string) {
    this.messageService.add(`DataService: ${message}`);
  }

  /** GET Datas from the server */
  //This works.
  getDataArray(): Observable<User[]> {
    return this.http.get<User[]>(this.usersUrl)
      .pipe(
        tap(_ => this.log('fetched Datas')),
        catchError(this.handleError<User[]>('getDataArray', []))
      );
  }

  /** GET Data by name. Return `undefined` when name match not found */
  getDataNo404<Data>(name: string): Observable<User> {
    //Could be an issue, considering .name is a class exclusive
    const url = `${this.usersUrl}/?name=${name}`;
    return this.http.get<User[]>(url)
      .pipe(
        map(Datas => Datas[0]), // returns a {0|1} element array
        tap(h => {
          const outcome = h ? 'fetched' : 'did not find';
          this.log(`${outcome} Data name=${name}`);
        }),
        catchError(this.handleError<User>(`getData name=${name}`))
      );
  }

  /** GET Data by name. Will 404 if name not found */
  //With users database, works fine.
  getData(name: string): Observable<User> {
    const url = `${this.usersUrl}/${name}`;
    console.log(url);
    return this.http.get<User>(url).pipe(
      tap(_ => this.log(`fetched Data name=${name}`)),
      catchError(this.handleError<User>(`getData name=${name}`))
    );
  }

  /** PUT: update the Data on the server */
  //With users database, breaks.
  updateData(Data: User): Observable<any> {
    //Could be an issue, considering .name is a class exclusive
    return this.http.put(this.usersUrl, Data, this.httpOptions).pipe(
      tap(_ => this.log(`updated Data name=${Data.name}`)),
      catchError(this.handleError<any>('updateData'))
    );
  }
  /** POST: add a new Data to the server */
  addData(Data: User): Observable<User> {
    //.name is an exclusve, could be an issue.
    //Somehow does not work.
    //Data object is created properly, but not inserted at the url.
    
    return this.http.post<User>(this.usersUrl, Data, this.httpOptions).pipe(
      tap((newData: User) => this.log(`added user w/ name=${Data.name}`)),
      catchError(this.handleError<User>('addData'))
    );
  }

  /** DELETE: delete the Data from the server */
  deleteData(name: string): Observable<User> {
    const url = `${this.usersUrl}/${name}`;

    return this.http.delete<User>(url, this.httpOptions).pipe(
      tap(_ => this.log(`deleted Data name=${name}`)),
      catchError(this.handleError<User>('deleteData'))
    );
  }

  /* GET Datas whose name contains search term */
  searchDataArray(term: string): Observable<User[]> {
    if (!term.trim()) {
      // if not search term, return empty Data array.
      return of([]);
    }
    return this.http.get<User[]>(`${this.usersUrl}/?name=${term}`).pipe(
      tap(x => x.length ?
        this.log(`found Datas matching "${term}"`) :
        this.log(`no Datas matching "${term}"`)),
      catchError(this.handleError<User[]>('searchDataArray', []))
    );
  }

  /**
 * Handle Http operation that failed.
 * Let the app continue.
 *
 * @param operation - name of the operation that failed
 * @param result - optional value to return as the observable result
 */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
