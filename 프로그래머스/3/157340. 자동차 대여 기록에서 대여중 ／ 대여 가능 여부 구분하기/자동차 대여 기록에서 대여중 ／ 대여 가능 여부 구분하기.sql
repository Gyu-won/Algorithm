-- 코드를 입력하세요
# SELECT car_id, 
#     case when date_format(start_date, '%Y-%m-%d') <= '2022-10-16' and
#         date_format(end_date, '%Y-%m-%d') >= '2022-10-16' then '대여중'
#     else '대여 가능'
#     end as availability
# from car_rental_company_rental_history
# where 
# order by car_id desc;


select c.car_id, 
    case when m.flag = 1 then '대여중'
    else '대여 가능'
    end as availability
from car_rental_company_rental_history c 
join (
    select car_id, 
        max(case 
            when date_format(start_date, '%Y-%m-%d') <= '2022-10-16' 
                and date_format(end_date, '%Y-%m-%d') >= '2022-10-16' 
            then 1
            else 0
        end) as flag
    from car_rental_company_rental_history
    group by car_id) m 
on c.car_id = m.car_id
group by car_id
order by car_id desc;


# 2022년 10월 16일 대여 중인 자동차는 대여중 아니면 대여가능 (availability)
# 