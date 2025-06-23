create table invoice_details (
    gold_karat integer not null,
    price_per_gm integer not null,
    transaction_type tinyint check (transaction_type between 0 and 1) not null,
    wage_per_gm integer not null,
    weight_gm integer not null,
    weight_mg integer not null,
    invoice_details_id bigint not null auto_increment,
    invoice_info_invoice_id bigint,
    description varchar(255) not null,
    primary key (invoice_details_id)) engine=InnoDB;

create table invoice_info (
    invoice_date date not null,
    invoice_generated_date_time datetime(6) not null,
    invoice_id bigint not null auto_increment,
    invoice_sale_name_user_id bigint,
    customer_address varchar(255) not null,
    customer_name varchar(255) not null,
    customer_phone varchar(255) not null,
    primary key (invoice_id)) engine=InnoDB;

create table store (
    gold_in_mg integer not null,
    store_id bigint not null auto_increment,
    primary key (store_id)) engine=InnoDB;

create table user (
    authority tinyint not null check (authority between 0 and 1),
    disabled bit not null ,
    user_id bigint not null auto_increment,
    invoice_name varchar(255) not null,
    password varchar(255) not null,
    username varchar(255) not null,
    primary key (user_id)) engine=InnoDB;


alter table user add constraint UK6to7wyercyswfdra2trq9rap3 unique (invoice_name);
alter table user add constraint UKsb8bbouer5wak8vyiiy4pf2bx unique (username);
alter table invoice_details add constraint FK5jv0w678su6hugjw0wyi4cwti foreign key (invoice_info_invoice_id) references invoice_info (invoice_id);
alter table invoice_info add constraint FKsv79106ql0dhjmd0pf7b15gy8 foreign key (invoice_sale_name_user_id) references user (user_id);