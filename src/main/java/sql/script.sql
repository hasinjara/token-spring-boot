create table role(
    id_role varchar,
    role varchar,
    primary key (id_role)
);
insert into role(id_role, role) values
('R1','admin'),
('R2','default');

create sequence seq_users increment by 1 minvalue 1;
create table users (
   id_users varchar default 'U'||nextval('seq_users'),
   nom varchar,
   prenom varchar,
   mail varchar,
   mdp varchar,
   role varchar not null,
   primary key (id_users)
);