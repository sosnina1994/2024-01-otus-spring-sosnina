insert into tool_types(name)
values ('ТИП_1'),
       ('ТИП_2'),
       ('ТИП_3');

insert into manufacturers(name)
values ('ПРОИЗВОДИТЕЛЬ_1'),
       ('ПРОИЗВОДИТЕЛЬ_2'),
       ('ПРОИЗВОДИТЕЛЬ_3');

insert into tools(name, designation, type_id, manufacturer_id, count, minBalance)
values ('ИНСТРУМЕНТ_1', 'МАРКИРОВКА_1', 1, 1, 20, 20),
       ('ИНСТРУМЕНТ_2', 'МАРКИРОВКА_2', 2, 2, 10, 10)