import { Routes } from '@angular/router';
import { BookListComponent } from './pages/book-list/book-list.component';
import { BookEditComponent } from './pages/book-edit/book-edit.component';
import {CommentEditComponent} from "./pages/comment-edit/comment-edit.component";
import {CommentListComponent} from "./pages/comment-list/comment-list.component";

export const routes: Routes = [
  {path: '', redirectTo: 'book-list', pathMatch: "full"},

  {path: 'book-list', component: BookListComponent},
  {path: 'book-edit/:id', component: BookEditComponent},
  {path: 'book-edit', component: BookEditComponent},
  {path: 'comment-list/:id', component: CommentListComponent},
  {path: 'comment-edit', component: CommentEditComponent},
  {path: 'comment-edit/:id', component: CommentEditComponent},
];
