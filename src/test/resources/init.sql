#### oracle ####
#### 用户表 ####
create table bpos_user(
id number(11) not null primary key,
name varchar2(30) not null,
passwd varchar2(100) not null,
create_date date default sysdate,
last_login_date date ,
locked char(1) default 0,
constraint bpos_user_name unique(name)
)
comment on table bpos_user is '用户表';
comment on column  bpos_user.name is '用户名';
comment on column  bpos_user.passwd is '密码';
comment on column  bpos_user.create_date  is '创建时间';
comment on column  bpos_user.last_login_date  is '上次登入时间';
comment on column  bpos_user.locked is '是否锁定，0 正常，非0 锁定';

create sequence bpos_user_id_sequence 
minvalue 1                                  
maxvalue 999999999                
start with 1                                 
increment by 1                         
nocache;

create or replace trigger bpos_user_id_sequence_autoup
before insert on bpos_user                                          
for each row 
begin 
select bpos_user_id_sequence.nextval into :new.id from dual;   
end;


#### 角色表 ####
create table bpos_role(
id number(11) not null primary key,
name varchar2(30) not null,
status char(1) default 0,
constraint bpos_role_name unique(name)
)

comment on table bpos_role is '角色表';
comment on column  bpos_role.name is '角色名';
comment on column  bpos_role.status is '是否可用，0 正常，非0 不可用';

create sequence bpos_role_id_sequence 
minvalue 1                                  
maxvalue 999999999                
start with 1                                 
increment by 1                         
nocache;

create or replace trigger bpos_role_id_sequence_autoup
before insert on bpos_role                                          
for each row 
begin 
select bpos_role_id_sequence.nextval into :new.id from dual;   
end;

#### 权限表 ####
create table bpos_auth(
id number(11) not null primary key,
name varchar2(30) not null,
value varchar2(50) not null,
status char(1) default 0,
constraint bpos_auth_name unique(name),
constraint bpos_auth_value unique(value)
)

comment on table bpos_auth is '权限表';
comment on column  bpos_auth.name is '权限名';
comment on column  bpos_auth.value is '权限值';
comment on column  bpos_auth.status is '是否可用，0 正常，非0 不可用';

create sequence bpos_auth_id_sequence 
minvalue 1                                  
maxvalue 999999999                
start with 1                                 
increment by 1                         
nocache;

create or replace trigger bpos_auth_id_sequence_autoup
before insert on bpos_auth                                          
for each row 
begin 
select bpos_auth_id_sequence.nextval into :new.id from dual;   
end;

#### 用户角色关联表 ####
create table bpos_user_role(
user_id number(11) not null,
role_id number(11) not null
)

#### 角色权限关联表 ####
create table bpos_role_auth(
auth_id number(11) not null,
role_id number(11) not null
)