import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { MatFormField, MatFormFieldModule, MatLabel } from '@angular/material/form-field';
import { MatOption, MatSelect } from '@angular/material/select';
import { NgFor } from '@angular/common';
import { Book } from '../../model/book';
import { Author } from '../../model/author';
import { Genre } from '../../model/genre';
import { BookService } from '../../services/book.service';
import { AuthorService } from '../../services/author.service';
import { GenreService } from '../../services/genre.service';
import { MatButtonModule } from '@angular/material/button';
import { MatInput, MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-book-edit',
  standalone: true,
  imports: [MatFormField, MatInputModule, MatLabel, MatSelect, MatOption, MatButtonModule,
    NgFor, FormsModule, MatInput, MatFormFieldModule],
  templateUrl: './book-edit.component.html',
  styleUrl: './book-edit.component.scss'
})
export class BookEditComponent implements OnInit {
  book: Book;
  authors: Author[];
  genres: Genre[];

  constructor(private route:ActivatedRoute,
              private router:Router,
              private bookService: BookService,
              private authorService: AuthorService,
              private genreService: GenreService) {
    this.book = new Book()
    this.authors = [];
    this.genres = [];
  }

  saveBook() {
    this.bookService.saveBook(this.book).subscribe(() => this.goToBookList());
  }

  goToBookList() {
    this.router.navigate(['book-list'])
  }

  private getBookById(id: number) {
    if(id === null || id === undefined) {
      this.book = new Book();
      this.book.title = "";
      return;
    }

    this.bookService.getBook(id).subscribe(data => {
      this.book = data;

      if (typeof this.book.author !== undefined &&
        this.book.author?.id !== undefined) {
        this.authors.forEach(element => {
          if(element !== undefined && this.book.author?.id === element.id) {
            this.book.author = element;
            return;
          }
        });
      }

      if (typeof this.book.genre !== undefined &&
        this.book.genre?.id !== undefined) {
        this.genres.forEach(element => {
          if(element !== undefined && this.book.genre?.id === element.id) {
            this.book.genre = element;
            return;
          }
        });
      }

    });
  }

  ngOnInit(): void {
    this.authorService.getAuthorList().subscribe(data => {
      this.authors = data;
    });

    this.genreService.getGenreList().subscribe(data => {
      this.genres = data;
    });

    this.route.params.subscribe((params: Params) => {
      let id = params['id'];
      this.getBookById(id);
    });
  }
}
