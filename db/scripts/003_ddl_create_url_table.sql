create table if not exists url (
    id serial primary key not null,
    code VARCHAR(50) NOT NULL unique,
    count int not null,
    site_id int not null unique references site(id)
    );