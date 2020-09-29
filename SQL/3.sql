--Выберите наименование и мировые результаты по всем соревнованиям, установленные 12-05-2010 или 15-05-2010
select competition_name,world_record from competition where
set_date = '2010.05.12' or set_date = '2010.05.15';