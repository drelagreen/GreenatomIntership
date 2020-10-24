delete from user_role;
delete from usr;

insert into usr (id, active, password, username)
values (1,true,'$2a$08$0gZf6MQf.yUDlDA8.yK93.FnKo82GwtjHqk9B9w87Lk846V4nE2bC','guest'),
       (2,true,'$2a$08$0gZf6MQf.yUDlDA8.yK93.FnKo82GwtjHqk9B9w87Lk846V4nE2bC','test');

insert into user_role (user_id, roles)
values  (1, 'USER'),
        (1, 'ADMIN'),
        (2, 'USER');
