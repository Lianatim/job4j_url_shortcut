create table if not exists site (
    id serial primary key not null,
    name varchar(2000) not null,
    login VARCHAR(50) unique,
    password VARCHAR(100),
    enabled boolean default true,
    authority_id int not null references authorities(id)
    );

insert into authorities (authority) values ('ROLE_USER');
insert into authorities (authority) values ('ROLE_ADMIN');

insert into site (name, login, password, enabled, authority_id)
values ('liana', 'root', '$2a$10$wY1twJhMQjGVxv4y5dBC5ucCBlzkzT4FIGa4FNB/pS9GaXC2wm9/W', true,
        (select id from authorities where authority = 'ROLE_ADMIN'));