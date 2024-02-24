
set @hour := -1;

select (@hour:=@hour + 1) as hour, 
    (select count(*) as count
    from animal_outs
    where @hour = hour(datetime)) as count
from animal_outs
where @hour < 23;




# 시간별로
# 입양건수
# 시간대 순 정렬