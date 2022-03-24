create table if not exists roles (
id serial primary key,
role text
);

create table if not exists persons (
id serial primary key,
username text unique,
password text,
role_id int references roles(id)
);

create table if not exists rooms (
id serial primary key,
name text,
created timestamp,
person_id int references persons(id)
);

create table if not exists messages (
id serial primary key,
name text,
created timestamp,
room_id int references rooms(id),
person_id int references persons(id)
);