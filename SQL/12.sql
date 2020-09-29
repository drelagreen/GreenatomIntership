-- Выберите годы рождения всех спортсменов без повторений.
select year_of_birth year
from sportsman group by year order by year;