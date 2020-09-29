create trigger checkCompetitionInsert
    after insert on result
    execute procedure checkWorldRecord();