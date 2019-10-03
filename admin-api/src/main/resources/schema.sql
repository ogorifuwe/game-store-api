create schema if not exists spring_security;
use spring_security;

create table if not exists users(
    username varchar(50) not null primary key,
    password varchar(100) not null,
    enabled boolean not null
);

create table if not exists authorities (
    username varchar(50) not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key(username) references users(username));
    create unique index ix_auth_username on authorities (username,authority

insert into users (username, password, enabled) values ('employeeUser', '$2a$10$UtdcoY2vIcxNxk7a45awgeDib/WPCWtuaG1YblmZTgrONwriyFKNm', true);
insert into users (username, password, enabled) values ('teamLeadUser', '$2a$10$al/nlweSIC9aHIwolqZRzeww2FWH7wfWSyNjz.DDYjmZkl1yLvLou', true);
insert into users (username, password, enabled) values ('managerUser', '$2a$10$Gx/qqV6bxfjGlSXCH73Rq.a5HyjbbnZ5jaFo8A/13ENlImTRKN6fy', true);
insert into users (username, password, enabled) values ('adminUser', '$2a$10$9gKbtvpqiK2/5lEz.pQrcenCgy/EdY8QcbeGqzueFPNVCNL10YZXi', true);


insert into authorities (username, authority) values ('employeeUser', 'ROLE_EMPLOYEE');

insert into authorities (username, authority) values ('teamLeadUser', 'ROLE_EMPLOYEE');
insert into authorities (username, authority) values ('teamLeadUser', 'ROLE_TEAM_LEAD');

insert into authorities (username, authority) values ('managerUser', 'ROLE_EMPLOYEE');
insert into authorities (username, authority) values ('managerUser', 'ROLE_TEAM_LEAD');
insert into authorities (username, authority) values ('managerUser', 'ROLE_MANAGER');

insert into authorities (username, authority) values ('adminUser', 'ROLE_EMPLOYEE');
insert into authorities (username, authority) values ('adminUser', 'ROLE_TEAM_LEAD');
insert into authorities (username, authority) values ('adminUser', 'ROLE_MANAGER');
insert into authorities (username, authority) values ('adminUser', 'ROLE_ADMIN');





