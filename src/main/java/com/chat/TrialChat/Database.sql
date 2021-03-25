create database ChatStore;

use ChatStore;

create table user (id integer not null auto_increment, username varchar(255), primary key (id));

create table message (id integer not null auto_increment, message varchar(255), sendTime datetime, conversation_id integer, user_id integer, primary key (id));

create table conversation (id integer not null auto_increment, primary key (id));