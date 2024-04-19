import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable, catchError, throwError } from 'rxjs';
import { Genre } from '../model/genre';

@Injectable({
  providedIn: 'root'
})
export class GenreService {

  private endPoint: string = environment.apiUrl;

  constructor(private http: HttpClient) {}

  get contextPath(): string {
    return this.endPoint.concat('genres');
  }

  public getGenreList(): Observable<Genre[]> {
    return this.http.get<Genre[]>(this.contextPath).pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse) {
    let errorMsg = error.headers.get('errorMsgs')?.toString();
    errorMsg = errorMsg === undefined ? '' : errorMsg;
    console.log(errorMsg);
    return throwError(() => new Error(errorMsg));
  }
}
