-- 코드를 입력하세요
SELECT i.animal_id, i.name
from animal_ins i
    join animal_outs o
    on i.animal_id = o.animal_id
order by timestampdiff(second, i.datetime, o.datetime) desc
limit 2;

# 입양을 간 동물 중
# 보호시작일과 입양일 차이가 가장 큰 동물 두마리의 id와 이름

# 12:29