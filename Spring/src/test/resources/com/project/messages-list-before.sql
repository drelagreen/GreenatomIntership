delete from message;

insert into message (id, text, tag, user_id)
values (1, 'first', '1tag',1),
       (2, 'second', '2tag',1),
       (3, 'third', '1tag',1),
       (4, 'fourth', '3tag',1);

alter sequence hibernate_sequence restart with 10;