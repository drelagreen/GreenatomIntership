--Выведите список спортсменов в виде 'Спортсмен ' ['имя спортсмена']
-- показал результат' ['результат'] 'в городе' ['город']
create or replace function getString() returns setof text as
    $BODY$
    declare
        var1 text;
    begin
        for var1 in select  concat('Спортсмен ',sportsman_name,' показал результат ',result.result,' в городе ',city) from result
            inner join sportsman s on result.sportsman_id = s.sportsman_id
            order by sportsman_name
        loop
        return next var1;
        end loop;
        return;
        end
    $BODY$
    language 'plpgsql'
