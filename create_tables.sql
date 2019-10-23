drop table if exists users;
drop table if exists company;
drop view if exists company_users;
create table company (
	id int AUTO_INCREMENT primary key,
	company_name varchar(255) not null
);
create table users (
	user_id int AUTO_INCREMENT, -- Shoudl this be done, explore this TODO
	username varchar(50) not null,
	password varchar(60) not null,
	enabled boolean not null,
	name varchar(50) not null,
	surname varchar(50) not null,
	year_of_birth smallint not null,
	email varchar(255),
	company_id int,
	foreign key (company_id) references company(id) on delete set null,
	primary key (user_id, username)
);
create view company_users as select * from company left join users on company.id = users.company_id;

insert into company values(default, "Beta Tau Beta");


