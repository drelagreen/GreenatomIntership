--Выведите наименования соревнований, у которых дата установления мирового рекорда совпадает с датой проведения соревнований в - Москве 20-04-2015.
select competition_name from competition
   inner join result r on competition.competition_id = r.competition_id
where r.hold_date = competition.set_date  and city = 'Москва' and hold_date = '2015.04.20';