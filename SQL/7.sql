--Выберите города проведения соревнований, где результаты принадлежат множеству {13, 25, 17, 9}.
select city from result where
    result.result in (13,25,17,9)
    group by city;