import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';

import { BookListComponent } from './pages/book-list/book-list.component';
import { BookEditComponent } from './pages/book-edit/book-edit.component';

import { MatButtonModule } from '@angular/material/button'
import { MatTableModule } from '@angular/material/table';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatLabel } from '@angular/material/form-field';
import { NgFor } from '@angular/common';

import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    RouterLink,
    RouterLinkActive,

    HttpClientModule,

    MatButtonModule,
    MatTableModule,
    MatInputModule,
    MatLabel,
    MatSelectModule,
    MatFormFieldModule,

    NgFor,

    BookListComponent,
    BookEditComponent,
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'frontend';
}
