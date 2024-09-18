select table_schema, table_name
from information_schema.tables
where table_schema = 'public';

select * from entry_model;

create table entry_model(
    id serial,
    name varchar(255)
);

