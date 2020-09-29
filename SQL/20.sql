--Выведите имена всех спортсменов, у которых год рождения больше,
--чем год установления мирового рекорда, равного 12 с.
select year_of_birth from sportsman
    where (year_of_birth>(select min(extract(year from set_date)) from competition));
