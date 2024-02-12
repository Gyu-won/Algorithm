-- 코드를 입력하세요
# SELECT car_id, avg(timestampdiff(day, start_date, end_date) + 1) as average_duration
#     from car_rental_company_rental_history
#     group by car_id
    
select a.car_id, round(a.diff, 1) as average_duration
from (SELECT car_id, avg(timestampdiff(day, start_date, end_date) + 1) as diff
    from car_rental_company_rental_history
    group by car_id) a
where a.diff >= 7
order by average_duration desc, a.car_id desc;

# SELECT CAR_ID, ROUND(AVG(DATEDIFF(END_DATE, START_DATE)+1), 1) AS AVERAGE_DURATION
# FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
# GROUP BY CAR_ID
# HAVING AVG(DATEDIFF(END_DATE, START_DATE) + 1) >= 7
# ORDER BY AVERAGE_DURATION DESC, CAR_ID DESC