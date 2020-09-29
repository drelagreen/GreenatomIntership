--Выведите имена всех спортсменов, у которых разряд
-- ниже среднего разряда всех спортсменов, родившихся в 2000 году.
select sportsman_name from sportsman
where (select avg(rank) from sportsman where year_of_birth=2000)>rank;