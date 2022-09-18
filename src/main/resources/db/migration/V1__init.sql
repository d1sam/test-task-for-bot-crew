create table degrees
(
    id   serial primary key,
    name varchar(50) not null
);

insert into degrees(name)
values ('assistant'),
       ('associate professor'),
       ('professor');

create table lectors
(
    id         bigserial primary key,
    degree_id  integer references degrees,
    first_name varchar(50) not null,
    last_name  varchar(50) not null,
    salary     decimal
);

insert into lectors(degree_id, first_name, last_name, salary)
values (1, 'Ivan', 'Nechui-Levytsky', 5100.25),
       (1, 'Ivan', 'Kotliarevsky', 6750.5),
       (2, 'Volodymyr', 'Sosyura', 5290.52),
       (2, 'Vasyl', 'Stus', 5200.5),
       (3, 'Taras', 'Shevchenko', 7500),
       (2, 'Lesia', 'Ukrayinka', 6600.75),
       (3, 'Martin', 'Fowler', 7500.75),
       (1, 'Robert', 'Martin', 7050),
       (2, 'Felicia', 'Fields', 6600),
       (3, 'Dave', 'Ulrich', 7550),
       (1, 'Alice', 'Harmon', 5000);

create table departments
(
    id   serial primary key,
    name varchar(50) not null,
    head_id int references lectors
);

insert into departments(name, head_id)
values ('Creation', 5),
       ('IT', 7),
       ('Human Resources', 9);

create table departments_lectors
(
    department_id int references departments,
    lector_id     int references lectors,
    constraint unique_combination_dep_lec unique (department_id, lector_id)
);

insert into departments_lectors(department_id, lector_id)
values (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (1, 6),
       (2, 7),
       (2, 8),
       (3, 9),
       (3, 10),
       (3, 11),
       (1, 11),
       (2, 1),
       (3, 5),
       (2, 3);