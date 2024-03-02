set @Hour := -1;

select a.hour, ifnull(b.cnt, 0) as count
from (select (@Hour := @Hour + 1) as hour
        from animal_outs
        where @Hour < 23) a
    left join (select hour(datetime) as hour, count(*) as cnt
              from animal_outs
              group by hour(datetime)) b
    on a.hour = b.hour