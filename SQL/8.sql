--Выберите имена всех спортсменов, у которых год рождения 2000 и разряд не принадлежит множеству {3, 7, 9}.
select sportsman_name from sportsman
where year_of_birth=2000 and not (rank in (3,7,9));