--Вычислите минимальный год рождения спортсменов, которые имеют 1 разряд.
select min(year_of_birth) from sportsman where rank = 1;