--Выберите дату проведения всех соревнований, которые проводились в Москве и полученные на них результаты равны 10 секунд.
select hold_date from result
    inner join
    competition on result.competition_id = competition.competition_id
    where result.result=10 and city='Москва'
group by hold_date;
