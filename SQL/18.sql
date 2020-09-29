--Вычислите средний результат каждого из спортсменов.
select sportsman.sportsman_id,avg(result) from result
    inner join sportsman on result.sportsman_id = sportsman.sportsman_id
where result.sportsman_id = sportsman.sportsman_id
group by sportsman.sportsman_id
order by sportsman.sportsman_id;