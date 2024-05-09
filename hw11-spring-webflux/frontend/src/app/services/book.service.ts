import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { Book } from '../model/book';
import { environment } from '../../environments/environment';
import { BookData } from '../model/book-data';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  private endPoint: string = environment.apiUrl;

  constructor(private http: HttpClient) {}

  get contextPath(): string {
    return this.endPoint.concat('books');
  }

  getApiWithId(id: number): string {
    let idStr = (id === null || id === undefined) ? '' : ("" + id);
    return this.endPoint.concat('books/', idStr);
  }

  public getAllBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(this.contextPath).pipe(
      catchError(this.handleError)
    );
  }

  public getBook(id: number): Observable<Book> {
    return this.http.get<Book>(this.getApiWithId(id)).pipe(
      catchError(this.handleError)
    );
  }

  public saveBook(book: Book): Observable<Book> {
    if(book.id !== undefined && book.id !== null) {
      return this.http.patch<Book>(this.getApiWithId(book.id), new BookData(book)).pipe(
        catchError(this.handleError)
      );
    } else {
      return this.http.post<Book>(this.contextPath, new BookData(book)).pipe(
        catchError(this.handleError)
      );
    }
  }

  public deleteBook(id: number) {
    return this.http.delete(this.getApiWithId(id)).pipe(
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
