--Определите количество участников с фамилией Иванов, которые участвовали
--в соревнованиях с названием, содержащим слово 'Региональные'.
select count(sportsman_name) from sportsman inner join
    result r on sportsman.sportsman_id = r.sportsman_id
    inner join competition c on r.competition_id = c.competition_id
where (position('Иванов' in sportsman_name)!=0 and position('Региональные' in competition_name)!=0);