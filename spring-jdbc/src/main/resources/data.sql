drop table if exists task;

create table task (
    id          integer primary key,
    name        varchar(255) not null,
    description varchar(255),
    difficult   integer
);

insert into task(id, name, description, difficult) values
(1, 'learn java', 'open book and learn java', 3),
(2, 'learn spring', 'open spring doc and learn spring-core, spring-security, spring-data, spring-aop', 3),
(3, 'learn data', 'open book and learn database', 2),
(4, 'see angular', 'learn angular in official documentation', 10),
(5, 'see typescript', 'learn typescript in official site', 4);

declare a integer(10);
