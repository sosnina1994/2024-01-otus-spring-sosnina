import {Component, OnInit} from '@angular/core';
import {MatButton} from "@angular/material/button";
import {Book} from "../../model/book";
import {Comment} from "../../model/comment";
import {CommentService} from "../../services/comment.service";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {MatTableModule} from "@angular/material/table";
import {BookService} from "../../services/book.service";

@Component({
  selector: 'app-comment-list',
  standalone: true,
  imports: [MatTableModule, MatButton],
  templateUrl: './comment-list.component.html',
  styleUrl: './comment-list.component.scss'
})
export class CommentListComponent implements OnInit {
  book: Book;
  comments: Comment[] = [];
  displayedColumns: string[] = ['id', 'text', 'actions'];

  constructor(private route:ActivatedRoute,
              private router:Router,
              private commentService: CommentService,
              private bookService: BookService) {
    this.book = new Book();
  }

  public goToEditComment(comment: Comment) {
    if (typeof comment === undefined || comment.id === undefined) {
      return;
    }
    this.router.navigate(['comment-edit', comment.id]);
  }

  public goToCreateComment() {
    this.router.navigate(['comment-edit']);
  }

  goToBookList() {
    this.router.navigate(['book-list'])
  }

  public deleteComment(comment: Comment) {
    if (typeof comment === undefined || comment.id === undefined) {
      return;
    }
    this.commentService.deleteComment(comment.id).subscribe(() => {
      this.refreshComments();
    });
  }

  private refreshComments() {
    if (this.book !== undefined) {
      let id = this.book.id;
      if (id === undefined) {
        return;
      }
      this.commentService.getCommentsForBook(id).subscribe(data => {
        this.comments = data;
      });
    }
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: Params) => {
      let id = params['id'];
      this.bookService.getBook(id).subscribe(data => {
        this.book = data;
        this.refreshComments();
      });
    });
  }
}
