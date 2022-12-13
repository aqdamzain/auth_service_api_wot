-- psql -U postgres --file webthingauth_sql.sql

drop database webthing_db;
drop user noir;
create user noir with password 'skripsi20222023';
create database webthing_db with template=DEFAULT owner=noir;
\connect webthing_db;
alter default privileges grant all on tables to noir;
alter default privileges grant all on sequences to noir;

create table tb_users(
user_id integer primary key not null,
username text UNIQUE not null,
password varchar(64) not null
);
create sequence tb_users_seq increment 1 start 1;

create table tb_devices(
device_id integer PRIMARY KEY not null,
rfid varchar(64) UNIQUE,
name varchar(64) not null
);
create sequence tb_devices_seq increment 1 start 1;

create table tb_gateways(
host varchar(64) not null,
api_link varchar(256) not null,
gateway_id varchar(64) UNIQUE not null
) INHERITS (tb_devices);


create table tb_things(
type varchar(64)[] not null,
web_id varchar(256) UNIQUE not null,
gateway_id varchar(64) not null
) INHERITS (tb_devices);
alter table tb_things add constraint my_things_rfid_fk foreign key (gateway_id) references tb_gateways(gateway_id);

create table tb_webthings(
host varchar(16) not null,
api_link varchar(256) not null,
web_id varchar(64) UNIQUE not null
) INHERITS (tb_devices);


create table tb_my_things(
user_id integer not null,
rfid varchar(64) not null,
my_things_type varchar(16) not null
);
alter table tb_my_things add constraint my_things_user_fk foreign key (user_id) references tb_users(user_id);
alter table tb_my_things add constraint my_things_rfid_fk foreign key (rfid) references tb_devices(rfid);