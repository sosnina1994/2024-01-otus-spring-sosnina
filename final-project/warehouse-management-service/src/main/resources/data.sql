insert into tool_types(name)
values ('Сверло'),
       ('Развертка'),
       ('Пластина токарная');

insert into manufacturers(name)
values ('Sandvik'),
       ('Seco'),
       ('Инструментальных цех №4');

insert into tools(name, designation, type_id, manufacturer_id, count, minBalance)
values ('Пластина для точения', 'DCMT070204-KM 3210', 3, 1, 20, 20),
       ('Развертка ГОСТ 3509-71', '2362-0007 D14', 2, 2, 10, 10)