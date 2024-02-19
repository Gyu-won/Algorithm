-- 코드를 입력하세요
select date_format(sales_date, '%Y-%m-%d') as sales_date, product_id, user_id, sales_amount
from (SELECT sales_date, product_id, user_id, sales_amount
    from online_sale
    where date_format(sales_date, '%Y-%m') = '2022-03'
    union        
    select sales_date, product_id, null as user_id, sales_amount
    from offline_sale
    where date_format(sales_date, '%Y-%m') = '2022-03') as sale
order by sales_date, product_id, user_id;


# 11:31