-- 코드를 입력하세요

set @Hour = -1;

select (@Hour := @Hour+1) as hour, 
    (SELECT count(*)
    from animal_outs
     where hour(datetime) = @Hour) as count

from animal_outs

where @Hour < 23

order by hour



#17:43