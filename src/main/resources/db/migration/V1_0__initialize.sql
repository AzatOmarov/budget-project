create table user
(
  ID           bigint      not null auto_increment,
  NAME         varchar(50),
  EMAIL        varchar(50) not null,
  PASSWORD     varchar(50) not null,
  CREATED_DATE datetime    not null,
  UPDATED_DATE datetime    not null,
  primary key (ID)
);
create table wallet
(
  ID       bigint      not null auto_increment,
  USER_ID  bigint,
  CURRENCY varchar(50) not null,
  primary key (ID)
);
create table category_type
(
  ID   bigint not null auto_increment,
  NAME varchar(50),
  primary key (ID)
);
create table category
(
  ID            bigint      not null auto_increment,
  CATEGORY_TYPE integer      not null,
  NAME          varchar(50) not null,
  primary key (ID)
);
create table transaction
(
  ID           bigint         not null auto_increment,
  CUSTOM_DATE  datetime       not null,
  AMOUNT       decimal(19,2) not null,
  CURRENCY     varchar(255),
  WALLET_ID    bigint,
  CATEGORY_ID  bigint,
  DESCRIPTION  varchar(255),
  CREATED_DATE datetime       not null,
  UPDATED_DATE datetime       not null,
  primary key (ID)
);
alter table user
  add constraint USER_EMAIL unique (EMAIL);
alter table category
  add constraint CATEGORY_NAME unique (NAME);
alter table wallet
  add constraint WALLET_USER_ID_USER_ID foreign key (USER_ID) references user (ID) ON DELETE CASCADE;
# alter table category
#   add constraint CATEGORY_CATEGORY_TYPE_CATEGORY_TYPE foreign key (ID) references category_type (ID) ON DELETE CASCADE;
alter table transaction
  add constraint TRANSACTION_WALLET_ID_WALLET_ID foreign key (WALLET_ID) references wallet (ID) ON DELETE CASCADE;
alter table transaction
  add constraint TRANSACTION_CATEGORY_ID_CATEGORY_ID foreign key (CATEGORY_ID) references category (ID) ON DELETE CASCADE;
