--Выберите имена всех спортсменов, у которых имена начинаются с буквы "М" и год рождения не заканчивается на "6".
select sportsman_name from sportsman
where sportsman_name like 'М%' and year_of_birth % 10 != 6;