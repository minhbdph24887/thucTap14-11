create table roles(
	id bigserial primary key,
	code varchar(200) default null,
	name varchar(200) default null,
	status int default 1
);

create table accounts(
	id bigserial primary key,
	code varchar(200) default null,
	name varchar(200) default null,
	birthday date default null,
	gender bit default null,
	address varchar(200) default null,
	id_role bigint default null,
	constraint fk_roles foreign key (id_role) references roles(id)
);