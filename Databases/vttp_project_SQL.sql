-- create schema  `vttp_project`;
use vttp_project;

create table user_data(
	user_data_id varchar(8) not null,
	email varchar(128) not null,
    display_name varchar(128) not null,
    user_password varchar(128) not null,
    image_url varchar(256),
    constraint pk_user_data_id primary key (user_data_id)
);

insert into user_data (user_data_id, email, display_name, user_password, image_url) values ("a","a","a","a","a");
select * from user_data where (email, user_password) = ("a", "a");
create table user_settings(
	user_settings_id varchar(8) not null,
    user_data_id varchar(8) not null,
    email_notif boolean default(true),
    tele_notif boolean default(true),
	constraint pk_user_settings_id primary key (user_settings_id),
    constraint fk_user_data_settings_id foreign key (user_data_id) references user_data(user_data_id)
);


-- insert into event_details (event_details_id, user_data_id, title, description, image_url, category) values 
-- 	("dean134s","a","Dog Event","dogdog", "https://vttp-davinia.sgp1.digitaloceanspaces.com/image/eac5a4b5", "lifestyle");
-- insert into event_details (event_details_id, user_data_id, title, description, image_url, category) values
-- 	("catn134s","a","Cute Cat Event","catcatcat", "https://vttp-davinia.sgp1.digitaloceanspaces.com/image/1ad03d65", "lifestyle");
-- insert into event_details (event_details_id, user_data_id, title, description, image_url, category) values 
-- 	("runn134s","a","Standard Chartered Marathon","runrunrunrunrun runrunrunrun", 
--     "https://onecms-res.cloudinary.com/image/upload/s--a3dWwnNL--/c_fill,g_auto,h_468,w_830/f_auto,q_auto/standard-chartered-singapore-marathon-2017.jpg", "sports");
-- insert into event_details (event_details_id, user_data_id, title, description, image_url, category) values
-- 	("nusn134s","a","NUS Supernova","Great music and FUN!", "https://content.presspage.com/uploads/2580/1920_crowd2.jpg", "music");
-- select * from event_details;
-- delete from event_details where event_details_id = "a";

-- drop table booth_details;
-- drop table event_details;

-- create table event_details(
-- 	event_details_id varchar(8) not null,
--     user_data_id varchar(8) not null,
--     title varchar(128) not null,
--     description text not null,
--     image_url text not null,
--     category enum("lifestyle", "food", "music", "sports") not null,
--     constraint pk_event_details_id primary key (event_details_id),
--     constraint fk_user_data_event_id foreign key (user_data_id) references user_data(user_data_id)
-- );

create table event_details(
	event_details_id varchar(8) not null,
    user_data_id varchar(8) not null,
    constraint pk_event_details_id primary key (event_details_id),
    constraint fk_user_data_event_id foreign key (user_data_id) references user_data(user_data_id)
);

insert into event_details (event_details_id, user_data_id) values ("dean134s","a");
insert into event_details (event_details_id, user_data_id) values ("catn134s","a");
insert into event_details (event_details_id, user_data_id) values ("runn134s","a");
insert into event_details (event_details_id, user_data_id) values ("nusn134s","a");

create table booth_details(
	booth_id varchar(8) not null,
    user_data_id varchar(8) not null,
    event_details_id varchar(8) not null,
    booth_name varchar(128) not null,
    description text not null,
    constraint pk_booth_id primary key (booth_id),
    constraint fk_user_data_booth_id foreign key (user_data_id) references user_data(user_data_id),
    constraint fk_event_details_id foreign key (event_details_id) references event_details(event_details_id)
);