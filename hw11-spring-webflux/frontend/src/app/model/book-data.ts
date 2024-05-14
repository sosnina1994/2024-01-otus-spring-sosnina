import { Book } from "./book";

export class BookData {
  id?: number | undefined;
  title?: string | null;
  authorId?: number | undefined;
  genreId?: number | undefined;

  constructor(book: Book) {
    this.id = book.id;
    this.title = book.title;
    this.authorId = book.author?.id;
    this.genreId = book.genre?.id;
  }
}
