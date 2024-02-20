-- 코드를 입력하세요
# SELECT i.name, i.datetime
# from animal_ins i
#     left join animal_outs o
#     on i.animal_id = o.animal_id
# where o.animal_id is null
# order by i.datetime
# limit 3;

SELECT a.name, a.datetime
from animal_ins as a
left join animal_outs as b on a.animal_id=b.animal_id
where b.animal_id is NULL
order by DATE_FORMAT(a.datetime,"%Y-%m-%d")
limit 3;

# 01:53