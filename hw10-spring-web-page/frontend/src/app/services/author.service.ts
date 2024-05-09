import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse} from "@angular/common/http";
import { environment } from '../../environments/environment';
import { Observable, catchError, throwError } from 'rxjs';
import { Author } from '../model/author';

@Injectable({
  providedIn: 'root'
})
export class AuthorService {

  private endPoint: string = environment.apiUrl;

  constructor(private http: HttpClient) {}

  get contextPath(): string {
    return this.endPoint.concat('authors');
  }

  public getAuthorList(): Observable<Author[]> {
    return this.http.get<Author[]>(this.contextPath).pipe(
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
