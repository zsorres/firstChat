CREATE DATABASE ChatStore;
USE ChatStore;

CREATE TABLE user (
user_id int(11) NOT NULL AUTO_INCREMENT,
username varchar(45) NOT NULL,
PRIMARY KEY (user_id)
);

CREATE TABLE message (
message_id int(11) NOT NULL AUTO_INCREMENT,
message varchar(128) NOT NULL,
sendTime DATE NOT NULL ,
user int(11) NOT NULL,
PRIMARY KEY (message_id)
);
