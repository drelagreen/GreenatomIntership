--Определите имена спортсменов, у которых личные рекорды совпадают с результатами, установленными 12-04-2014.
select sportsman_name from sportsman
    inner join
    result on sportsman.sportsman_id = result.sportsman_id
where result.result = sportsman.personal_record and
      result.hold_date = '12-04-2014';