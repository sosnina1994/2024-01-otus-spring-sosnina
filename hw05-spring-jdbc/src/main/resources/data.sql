insert into authors(full_name)
values ('Станислав Лем'), ('Теодор Драйзер'), ('Стивен Кинг');

insert into genres(name)
values ('Научная фантастика'), ('Социальная драма'), ('Психологический триллер');

insert into books(title, author_id, genre_id)
values ('Эдем', 1, 1), ('Американская трагедия', 2, 2), ('Мизери', 3, 3);
