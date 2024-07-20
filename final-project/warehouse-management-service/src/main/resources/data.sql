insert into tool_types(name)
values ('Сверло'),
       ('Развертка'),
       ('Пластина токарная');

insert into tool_brands(name)
values ('Sandvik'),
       ('Seco'),
       ('Инструментальных цех №4');

insert into tools(name, designation, type_id, brand_id, balance, min_balance)
values ('Пластина для точения', 'DCMT070204-KM 3210', 3, 1, 20, 20),
       ('Развертка ГОСТ 3509-71', '2362-0007 D14', 2, 2, 10, 10);

insert into users(username, password, enabled)
values ('user', '93e74353855c4cfc1b68b3732f81e1f6e73896eddfe705cbbf74c0e7c2034b8dc110d84be216bd48cbacce84a240c54e',
        true),
       ('admin', '92c4f87bfc1f7fffa4bb0bf9edbaaf86543e2ca2d3068c46965c0ca5ba0b590c5d443b3dfc9fcbf0bdb20cd3600dda80',
        true);

insert into authorities(username, authority)
values ('admin', 'ROLE_ADMIN'),
       ('admin', 'ROLE_USER'),
       ('user', 'ROLE_USER');