insert into tool_types(name)
values ('ТИП_1'),
       ('ТИП_2'),
       ('ТИП_3');

insert into tool_brands(name)
values ('БРЕНД_1'),
       ('БРЕНД_2'),
       ('БРЕНД_3');

insert into tools(name, designation, type_id, brand_id, count, minBalance)
values ('ИНСТРУМЕНТ_1', 'МАРКИРОВКА_1', 1, 1, 20, 20),
       ('ИНСТРУМЕНТ_2', 'МАРКИРОВКА_2', 2, 2, 10, 10)