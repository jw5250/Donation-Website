import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Event } from './dataClasses/event';
import { MessageService } from './services/message.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class EventService {
  private needsUrl = 'http://localhost:8080/cupboard'  // URL to web api
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private messageService: MessageService) { } 
  
  /** Log a EventService message with the MessageService */
  private log(message: string) {
    this.messageService.add(`EventService: ${message}`);
  }

  /** GET needs from the server */
  getWholeEvent(): Observable<Event[]> {
    return this.http.get<Event[]>(this.needsUrl)
      .pipe(
        tap(_ => this.log('fetched needs')),
        catchError(this.handleError<Event[]>('getWholeEvent', []))
      );
  }

  /** GET need by name. Return `undefined` when name match not found */
  getEventNo404<Data>(name: string): Observable<Event> {
    const url = `${this.needsUrl}/?name=${name}`;
    return this.http.get<Event[]>(url)
      .pipe(
        map(needs => needs[0]), // returns a {0|1} element array
        tap(h => {
          const outcome = h ? 'fetched' : 'did not find';
          this.log(`${outcome} need name=${name}`);
        }),
        catchError(this.handleError<Event>(`getEvent name=${name}`))
      );
  }

    /** GET need by name. Will 404 if name not found */
  getEvent(name: string): Observable<Event> {
    const url = `${this.needsUrl}/${name}`;
    return this.http.get<Event>(url).pipe(
      tap(_ => this.log(`fetched need name=${name}`)),
      catchError(this.handleError<Event>(`getEvent name=${name}`))
    );
  }
  /** PUT: update the need on the server */
  updateEvent(need: Event): Observable<any> {
    return this.http.put(this.needsUrl, need, this.httpOptions).pipe(
      tap(_ => this.log(`updated need name=${need.name}`)),
      catchError(this.handleError<any>('updateEvent'))
    );
  }
  /** POST: add a new need to the server */
  addEvent(need: Event): Observable<Event> {
    return this.http.post<Event>(this.needsUrl, need, this.httpOptions).pipe(
      tap((newEvent: Event) => this.log(`added need w/ name=${newEvent.name}`)),
      catchError(this.handleError<Event>('addEvent'))
    );
  }

  /** DELETE: delete the need from the server */
  deleteEvent(name: string): Observable<Event> {
    const url = `${this.needsUrl}/${name}`;

    return this.http.delete<Event>(url, this.httpOptions).pipe(
      tap(_ => this.log(`deleted need name=${name}`)),
      catchError(this.handleError<Event>('deleteEvent'))
    );
  }

  /* GET needs whose name contains search term */
  searchEvents(term: string): Observable<Event[]> {
    if (!term.trim()) {
      // if not search term, return empty need array.
      return of([]);
    }
    return this.http.get<Event[]>(`${this.needsUrl}/?name=${term}`).pipe(
      tap(x => x.length ?
        this.log(`found needs matching "${term}"`) :
        this.log(`no needs matching "${term}"`)),
      catchError(this.handleError<Event[]>('searchEvents', []))
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

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for Event consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
