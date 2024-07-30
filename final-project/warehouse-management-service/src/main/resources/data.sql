-- Загрузка типов инструмента
insert into tool_types(name)
values ('Сверло'),
       ('Развертка'),
       ('Пластина токарная');

-- Загрузка брендов инструмента
insert into tool_brands(name)
values ('Sandvik'),
       ('Seco'),
       ('Инструментальных цех №4');

-- Загрузка инструментов
insert into tools(name, designation, type_id, brand_id)
values ('Пластина для точения', 'DCMT070204-KM 3210', 3, 1),
       ('Развертка ГОСТ 3509-71', '2362-0007 D14', 2, 2);

-- Балансы инструментов
insert into tool_balances(tool_id, current_balance, min_balance)
values (1, 8, 10),
       (2, 15, 3);

-- Акты выдачи инструмента
insert into issue_reports (tool_id, count, route_card_number, product_cipher, operation_number, workplace_number,
                           employee_name, create_at)
values (1, 1, '0245R55dxd5552sd', 'ПИКВ.116.02.03', '030', '0455482', 'Иванов Иван Иванович', '2024-07-20 00:00:00.000');

-- Акты поступления инструмента
insert into arrival_reports(order_number, tool_id, count, create_at)
values ('Заявка 145567-75', 1, 8, '2024-07-20 00:00:00.000');

-- Пользователи для авторизации
insert into users(username, password, enabled)
values ('user', '93e74353855c4cfc1b68b3732f81e1f6e73896eddfe705cbbf74c0e7c2034b8dc110d84be216bd48cbacce84a240c54e',
        true),
       ('admin', '92c4f87bfc1f7fffa4bb0bf9edbaaf86543e2ca2d3068c46965c0ca5ba0b590c5d443b3dfc9fcbf0bdb20cd3600dda80',
        true);

insert into authorities(username, authority)
values ('admin', 'ROLE_ADMIN'),
       ('admin', 'ROLE_USER'),
       ('user', 'ROLE_USER');

