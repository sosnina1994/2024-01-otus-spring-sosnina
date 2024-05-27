insert into authors(id, full_name)
values (1, 'Author_1'),
       (2, 'Author_2'),
       (3, 'Author_3');

insert into genres(id, name)
values (1, 'Genre_1'),
       (2, 'Genre_2'),
       (3, 'Genre_3');

insert into books(title, author_id, genre_id)
values ('BookTitle_1', 1, 1),
       ('BookTitle_2', 2, 2),
       ('BookTitle_3', 3, 3);

insert into comments(book_id, text)
values (1, 'Book_1_Comment_1'),
       (1, 'Book_1_Comment_2'),
       (1, 'Book_1_Comment_3'),
       (2, 'Book_2_Comment_1'),
       (3, 'Book_3_Comment_1');