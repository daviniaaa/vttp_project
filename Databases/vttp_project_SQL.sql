create schema  `vttp_project`;

create table user_data(
	email varchar(128) not null,
    username varchar(128) not null,
    user_password varchar(128) not null,
    user_settings_id int not null,
    constraint pk_email primary key (email),
    constraint fk_user_settings_id foreign key (settings_id) references user_settings(id)
);

create table user_settings(
	id int not null,
    user_data_email varchar(128) not null,
    email_notif boolean default(true),
    tele_notif boolean default(true),
    profile_picture_url text,
	constraint pk_id primary key (id),
    constraint fk_user_data_email foreign key (user_data_email) references user_data(email)
);

create table post_details(
	id int not null,
    description text not null,
    image_url text,
    user_data_email varchar(128) not null,
    constraint pk_id primary key (id),
    constraint fk_user_data_email foreign key (user_data_email) references user_data(email)
);