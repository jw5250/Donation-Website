import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { MessageService } from './message.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import { DonationReward } from '../dataClasses/DonationReward';
@Injectable({
  providedIn: 'root'
})
export class DonationRewardService {
  private DonationRewardsUrl = 'http://localhost:8080/donationRewards'  // URL to web api
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private messageService: MessageService) { } 
  
  /** Log a DonationRewardService message with the MessageService */
  private log(message: string) {
    this.messageService.add(`DonationRewardService: ${message}`);
  }

  /** GET DonationRewards from the server */
  getDonationRewards(): Observable<DonationReward[]> {
    return this.http.get<DonationReward[]>(this.DonationRewardsUrl)
      .pipe(
        tap(_ => this.log('fetched DonationRewards')),
        catchError(this.handleError<DonationReward[]>('getCupboard', []))
      );
  }

  /** GET DonationReward by name. Return `undefined` when name match not found */
  getDonationRewardNo404<Data>(name: string): Observable<DonationReward> {
    const url = `${this.DonationRewardsUrl}/?name=${name}`;
    return this.http.get<DonationReward[]>(url)
      .pipe(
        map(DonationRewards => DonationRewards[0]), // returns a {0|1} element array
        tap(h => {
          const outcome = h ? 'fetched' : 'did not find';
          this.log(`${outcome} DonationReward name=${name}`);
        }),
        catchError(this.handleError<DonationReward>(`getDonationReward name=${name}`))
      );
  }

    /** GET DonationReward by name. Will 404 if name not found */
  getDonationReward(name: string): Observable<DonationReward> {
    const url = `${this.DonationRewardsUrl}/${name}`;
    return this.http.get<DonationReward>(url).pipe(
      tap(_ => this.log(`fetched DonationReward name=${name}`)),
      catchError(this.handleError<DonationReward>(`getDonationReward name=${name}`))
    );
  }
  /** PUT: update the DonationReward on the server */
  updateDonationReward(DonationReward: DonationReward): Observable<any> {
    return this.http.put(this.DonationRewardsUrl, DonationReward, this.httpOptions).pipe(
      tap(_ => this.log(`updated DonationReward name=${DonationReward.name}`)),
      catchError(this.handleError<any>('updateDonationReward'))
    );
  }
  /** POST: add a new DonationReward to the server */
  addDonationReward(DonationReward: DonationReward): Observable<DonationReward> {
    return this.http.post<DonationReward>(this.DonationRewardsUrl, DonationReward, this.httpOptions).pipe(
      tap((newDonationReward: DonationReward) => this.log(`added DonationReward w/ name=${newDonationReward.name}`)),
      catchError(this.handleError<DonationReward>('addDonationReward'))
    );
  }

  /** DELETE: delete the DonationReward from the server */
  deleteDonationReward(name: string): Observable<DonationReward> {
    const url = `${this.DonationRewardsUrl}/${name}`;

    return this.http.delete<DonationReward>(url, this.httpOptions).pipe(
      tap(_ => this.log(`deleted DonationReward name=${name}`)),
      catchError(this.handleError<DonationReward>('deleteDonationReward'))
    );
  }

  /* GET DonationRewards whose name contains search term */
  searchDonationRewards(term: string): Observable<DonationReward[]> {
    if (!term.trim()) {
      // if not search term, return empty DonationReward array.
      return of([]);
    }
    return this.http.get<DonationReward[]>(`${this.DonationRewardsUrl}/?name=${term}`).pipe(
      tap(x => x.length ?
        this.log(`found DonationRewards matching "${term}"`) :
        this.log(`no DonationRewards matching "${term}"`)),
      catchError(this.handleError<DonationReward[]>('searchDonationRewards', []))
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

      // TODO: better job of transforming error for DonationReward consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
