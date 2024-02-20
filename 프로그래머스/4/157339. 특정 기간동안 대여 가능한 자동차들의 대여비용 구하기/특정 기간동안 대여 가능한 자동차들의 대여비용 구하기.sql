-- 코드를 입력하세요

select car.car_id, car.car_type, 
    floor(car.daily_fee * (100 - discount.discount_rate) / 100 * 30) as fee

# 30일 할인 따로 구해서 join 하고
from car_rental_company_car car
    join (select car_type, discount_rate
         from car_rental_company_discount_plan
         where duration_type = '30일 이상') discount
    
    on car.car_type = discount.car_type

where car.car_type in ('세단', 'suv')

    and
    
    car.car_id not in (
        select car_id
        from car_rental_company_rental_history
        where date_format(start_date, '%Y-%m-%d') <= '2022-11-30'
            and
            date_format(end_date, '%Y-%m-%d') >= '2022-11-01'
    )
    
    and car.daily_fee * (100 - discount.discount_rate) / 100 * 30 between 500000 and 1990000

order by fee desc, car.car_type, car.car_id desc;
# 11월 1일 부터 11월 30일 대여 불가능한 차의 id를 뽑고
# not in 하고, 자동차 종류가 'suv', '세단' 인거

# 할인 금액 계산해서 정리
    

#10:05