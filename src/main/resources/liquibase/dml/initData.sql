--liquibase formatted sql

--changeset Sava:init_pass
insert into pass(id,duration,classnum,passcost)
values (1,1,4,2000);
insert into pass(id,duration,classnum,passcost)
values (2,1,8,3750);
insert into pass(id,duration,classnum,passcost)
values (3,2,12,5000);
insert into pass(id,duration,classnum,passcost)
values (4,2,16,6500);
insert into pass(id,duration,classnum,passcost)
values (5,3,24,8500);

insert into users(id,password,username)
values (1, '123', 'Sava');
insert into users(id,password,username)
values (2, '123', 'Kristina');
insert into users(id,password,username)
values (3, '123', 'admin');

insert into roles(id,role)
values (1, 'ROLE_OWNER');
insert into roles(id,role)
values (2, 'ROLE_ADMIN');
insert into roles(id,role)
values (3, 'ROLE_USER');