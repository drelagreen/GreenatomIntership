drop event trigger if exists checkcompetitioninsert;
drop function if exists checkWorldRecord();
create or replace function checkWorldRecord() returns trigger as
$BODY$
    declare
        res int;
        id int;
        comp_id int;
begin
    if tg_op= 'INSERT' then
        begin
    id = (select max(result_id) from result);
    comp_id = (select max(competition_id) from result where result_id = id);
    res = (select max(result) from result where result_id = id);
    if (select max(world_record) from competition where competition_id = comp_id) < res then
        begin
        update competition set
                            world_record = res, set_date = (select max(hold_date) from result where result_id = id)
        where competition_id = comp_id ;
        end;
    end if;
    end;
    end if;
    return null;
end
$BODY$
language 'plpgsql';

create trigger checkCompetitionInsert after insert on result
execute procedure checkWorldRecord();

insert into result (competition_id, sportsman_id, result, city, hold_date, result_id)
values
(10,15,70,'Варшава','27-9-2020',30);
