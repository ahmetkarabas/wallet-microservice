
insert into player (id,name) values (1,'test1');
insert into player (id,name) values (2,'test2');
insert into player (id,name) values (3,'test3');
insert into player (id,name) values (4,'test4');

insert into account (id, balance, name, player_id, version) values (4, 20, 'test1 account', 1, 1);
insert into account (id, balance, name, player_id, version) values (3, 40, 'test2 account', 2, 1);
insert into account (id, balance, name, player_id, version) values (2, 60, 'test3 account', 3, 1);
insert into account (id, balance, name, player_id, version) values (1, 80, 'test4 account', 4, 1);