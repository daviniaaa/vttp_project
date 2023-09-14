use railway;

create table user_data(
	user_data_id varchar(8) not null,
	email varchar(128) not null,
    display_name varchar(128) not null,
    user_password varchar(128) not null,
    image_url varchar(256),
    constraint pk_user_data_id primary key (user_data_id)
);

create table user_settings(
	user_settings_id varchar(8) not null,
    user_data_id varchar(8) not null,
    email_notif boolean default(true),
    tele_notif boolean default(true),
	constraint pk_user_settings_id primary key (user_settings_id),
    constraint fk_user_data_settings_id foreign key (user_data_id) references user_data(user_data_id)
);

create table booth_details(
	booth_id varchar(8) not null,
    user_data_id varchar(8) not null,
    event_details_id varchar(35) not null,
    booth_name varchar(128) not null,
    description text not null,
    constraint pk_booth_id primary key (booth_id),
    constraint fk_user_data_booth_id foreign key (user_data_id) references user_data(user_data_id) -- ,
    -- constraint fk_event_details_id foreign key (event_details_id) references event_details(event_details_id)
);