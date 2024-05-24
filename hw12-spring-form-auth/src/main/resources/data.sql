insert into authors(full_name)
values ('Станислав Лем'),
       ('Теодор Драйзер'),
       ('Стивен Кинг');

insert into genres(name)
values ('Научная фантастика'),
       ('Социальная драма'),
       ('Психологический триллер');

insert into books(title, author_id, genre_id)
values ('Эдем', 1, 1),
       ('Американская трагедия', 2, 2),
       ('Мизери', 3, 3);

insert into comments (text, book_id)
    values ('После прочтения стало моим любимым произведением', 1),
           ('Динамический сюжет и интересные персонажи', 1),
           ('Очень большое произведение. Временами нагоняет скуку', 2),
           ('Любимая книга после Гэтсби', 2),
           ('Слишком реалистично', 3),
           ('Одна из лучших работ Кинга', 3);

insert into users(username, password, enabled)
values ('user', '93e74353855c4cfc1b68b3732f81e1f6e73896eddfe705cbbf74c0e7c2034b8dc110d84be216bd48cbacce84a240c54e',
        true),
       ('admin', '92c4f87bfc1f7fffa4bb0bf9edbaaf86543e2ca2d3068c46965c0ca5ba0b590c5d443b3dfc9fcbf0bdb20cd3600dda80',
        true);

insert into authorities(username, authority)
values ('admin', 'ROLE_ADMIN'),
       ('admin', 'ROLE_USER'),
       ('user', 'ROLE_USER');