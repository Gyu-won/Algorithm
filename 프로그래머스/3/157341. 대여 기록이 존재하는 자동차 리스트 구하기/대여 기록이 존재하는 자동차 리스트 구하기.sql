-- 코드를 입력하세요
SELECT distinct(c.car_id)
from car_rental_company_car c 
    join car_rental_company_rental_history h
    on c.car_id = h.car_id
where c.car_type = '세단' 
    and month(h.start_date) = 10
order by c.car_id desc;


# 자동차의 종류가 세단인 자동차 들 중
# start_date가 10월이 하나라도 있는 car_id를 출력
# id는 중복 없어야하고, 

# 12:12