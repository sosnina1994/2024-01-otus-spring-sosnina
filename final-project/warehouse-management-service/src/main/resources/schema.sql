create table if not exists tool_types
(
    id   bigserial,
    name varchar(255),
    primary key (id)
);

create table if not exists manufacturers
(
    id   bigserial,
    name varchar(255),
    primary key (id)
);

create table if not exists tools
(
    id              bigserial,
    name            varchar(255),
    designation     varchar(255),
    type_id         bigint references tool_types (id),
    manufacturer_id bigint references manufacturers (id),
    count           int,
    minBalance      int,
    primary key (id)
);
