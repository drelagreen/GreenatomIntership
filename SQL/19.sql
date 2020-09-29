--Выведите годы рождения спортсменов, у которых результат,
--показанный в Москве выше среднего по всем спортсменам.

select year_of_birth from sportsman inner join
    result r on sportsman.sportsman_id = r.sportsman_id
where r.city = 'Москва' and r.result > (select avg(result) from result);