CREATE TABLE authorities (
 id serial primary key,
 authority VARCHAR(50) NOT NULL unique
);

create table if not exists users (
    id serial primary key not null,
    name varchar(2000),
    login VARCHAR(50) NOT NULL unique,
    password VARCHAR(100) NOT NULL,
    enabled boolean default true,
    authority_id int not null references authorities(id)
    );

insert into authorities (authority) values ('ROLE_USER');
insert into authorities (authority) values ('ROLE_ADMIN');

insert into users (name, login, enabled, password, authority_id)
values ('liana', 'root', true, '$2a$10$wY1twJhMQjGVxv4y5dBC5ucCBlzkzT4FIGa4FNB/pS9GaXC2wm9/W',
        (select id from authorities where authority = 'ROLE_ADMIN'));