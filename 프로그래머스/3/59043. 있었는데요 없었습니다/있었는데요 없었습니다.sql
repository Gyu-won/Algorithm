-- 코드를 입력하세요
SELECT o.animal_id, o.name
from animal_ins i
    join animal_outs o
    on i.animal_id = o.animal_id
where i.datetime > o.datetime
order by i.datetime

# 01:44