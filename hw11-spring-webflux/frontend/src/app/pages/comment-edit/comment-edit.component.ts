import {Component, OnInit} from '@angular/core';
import {MatFormField, MatFormFieldModule, MatLabel} from "@angular/material/form-field";
import {MatInput, MatInputModule} from "@angular/material/input";
import {FormsModule} from "@angular/forms";
import {NgFor} from "@angular/common";
import {MatOption, MatSelect} from "@angular/material/select";
import {MatButtonModule} from "@angular/material/button";
import {Comment} from "../../model/comment";
import {Book} from "../../model/book";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {BookService} from "../../services/book.service";
import {CommentService} from "../../services/comment.service";
import { CommentData } from '../../model/comment-data';

@Component({
  selector: 'app-comment-edit',
  standalone: true,
  imports: [MatFormField, MatInputModule, MatLabel, MatSelect, MatOption, MatButtonModule,
    NgFor, FormsModule, MatInput, MatFormFieldModule],
  templateUrl: './comment-edit.component.html',
  styleUrl: './comment-edit.component.scss'
})
export class CommentEditComponent implements OnInit {
  comment: CommentData;
  books: Book[];


  constructor(private route:ActivatedRoute,
              private router:Router,
              private bookService: BookService,
              private commentService: CommentService) {
    this.comment = new Comment();
    this.books = [];
  }

  saveComment() {
    if (this.comment.id === undefined) {
      this.commentService.createComment(this.comment).subscribe(() => this.goToBookList());
    } else {
      this.commentService.updateComment(this.comment.id, this.comment).subscribe(() => this.goToBookList());
    }
  }

  goToBookList() {
    this.router.navigate(['book-list'])
  }

  private getCommentById(id: number) {
    if(id === null || id === undefined) {
      this.comment = new Comment();
      this.comment.text = "";
      return;
    }

    this.commentService.getComment(id).subscribe(data => {
      this.comment = data;

      if (typeof this.comment.bookId !== undefined) {
        this.books.forEach(element => {
          if(element !== undefined && this.comment.bookId === element.id) {
            this.comment.bookId = element.id;
            return;
          }
        });
      }
    });
  }

  ngOnInit(): void {
    this.bookService.getAllBooks().subscribe(data => {
      this.books = data;
    });

    this.route.params.subscribe((params: Params) => {
      let id = params['id'];
      this.getCommentById(id);
    });
  }
}
