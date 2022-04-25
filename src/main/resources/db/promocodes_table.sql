create table promocodes_table (
                                  id varchar(36) primary key not null,
                                  value varchar(36),
                                  redeem boolean not null,
                                  redeem_date date
);