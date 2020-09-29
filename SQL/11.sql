--Выберите наименования всех соревнований, у которых в названии есть слово "международные".
select competition_name from competition
where substring(competition_name,'международные')='международные' or
        substring(competition_name,'Международные')='Международные';