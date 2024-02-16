-- 코드를 입력하세요
SELECT hour(datetime) as hour, count(*) as count
from animal_outs
where hour(datetime) between 9 and 19
group by hour(datetime)

# 9시 부터 19 59분 까지 시간대별 입양이 몇건이 발생했는지
order by hour;