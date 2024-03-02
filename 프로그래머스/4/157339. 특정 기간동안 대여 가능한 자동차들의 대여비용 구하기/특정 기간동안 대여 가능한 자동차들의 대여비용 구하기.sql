-- 코드를 입력하세요

select car.car_id, car.car_type, floor(30 * car.daily_fee * (100 - plan.discount_rate) / 100) as fee
from car_rental_company_car car
    join car_rental_company_discount_plan plan
    on car.car_type = plan.car_type
where plan.duration_type = '30일 이상'
    and car.car_type in ('세단', 'SUV')
    and car.car_id not in (select car_id
                    from car_rental_company_rental_history
                    where date_format(start_date, '%Y-%m-%d') <= '2022-11-30'
                        and date_format(end_date, '%Y-%m-%d') >= '2022-11-01')
    and 30 * car.daily_fee * (100 - plan.discount_rate) / 100 >= 500000
    and 30 * car.daily_fee * (100 - plan.discount_rate) / 100 < 2000000

order by fee desc, car.car_type, car.car_id desc;



# 자동차 종ㄹ가 세단, suv
# 20221101 - 20221130 대여 가능
# 30일간 대역ㅁ액이 50만-200만 미마ㄴ