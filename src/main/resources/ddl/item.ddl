create table item
(
	id integer not null
		constraint item_pk
			primary key,
	color varchar,
	grade varchar,
	name varchar
);

alter table item owner to postgres;

