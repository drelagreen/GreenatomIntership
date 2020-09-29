-- Выберите названия всех соревнований, у которых мировой рекорд равен 15 с и дата установки рекорда не равна 12-02-2015.
select competition_name from competition where
    world_record=15 and set_date!='2015.02.12';