create table bot_users (
                                  id varchar(36) primary key not null,
                                  username varchar(255),
                                  first_name varchar(255),
                                  last_name varchar(255),
                                  is_bot boolean not null,
                                  registration_date date
);
