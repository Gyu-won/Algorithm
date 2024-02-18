-- 코드를 입력하세요
SELECT month(start_date) as month, car_id, count(*) as records
from car_rental_company_rental_history
where car_id in (select car_id
                from car_rental_company_rental_history
                WHERE date_format(start_date, '%Y-%m') between '2022-08' and '2022-10'
                group by car_id
                having count(*) >= 5)
    and date_format(start_date, '%Y-%m') between '2022-08' and '2022-10'
group by month(start_date), car_id
having count(*) > 0
order by month, car_id desc;

# start_date 기준으로 2022년 8월 -2022 10월까지 count가 5회 이상 자동차들에 대해
# 해당 기간 동안 월별 + 자동차 id별 총 대여 횟수 as records, 0은 결과에서 제외
