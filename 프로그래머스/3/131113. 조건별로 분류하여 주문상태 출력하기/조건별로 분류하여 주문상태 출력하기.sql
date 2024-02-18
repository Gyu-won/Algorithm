-- 코드를 입력하세요
SELECT order_id, product_id, date_format(out_date, '%Y-%m-%d') as out_date, 
    case
        when date_format(out_date, '%Y-%m-%d') <= '2022-05-01' then '출고완료'
        when date_format(out_date, '%Y-%m-%d') > '2022-05-01' then '출고대기'
        else '출고미정'
    end as 출고여부 
from food_order
order by order_id;

# 출고여부는 out_date <= 5월 1일 출고완료 이후면 출고대기, null이면 출고미정