insert into authors(full_name)
values ('Author_1'),
       ('Author_2'),
       ('Author_3');

insert into genres(name)
values ('Genre_1'),
       ('Genre_2'),
       ('Genre_3');

insert into books(title, author_id, genre_id)
values ('Book_1', 1, 1),
       ('Book_2', 2, 2),
       ('Book_3', 3, 3);

insert into comments(book_id, text)
values (1, 'Book_1_Comment_1'),
       (1, 'Book_1_Comment_2'),
       (2, 'Book_2_Comment_1'),
       (2, 'Book_3_Comment_1');