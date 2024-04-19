import { Comment } from "./comment";

export class CommentData {
    id?: number;
    text?: string;
    bookId?: number;

    constructor(comment: Comment) {
      this.id = comment.id;
      this.text = comment.text;
      this.bookId = comment.book?.id;
    }
  }
