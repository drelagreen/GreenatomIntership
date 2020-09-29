--Выведите данные о спортсменах,
--у которых персональный рекорд совпадает с мировым.
select sportsman_name,sportsman_id,year_of_birth from sportsman
where personal_record in (select world_record from competition);