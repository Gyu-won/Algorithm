-- 코드를 입력하세요
SELECT p.product_id, p.product_name, sum(p.price * o.amount) as total_sales

from food_product p
    join food_order o
    on p.product_id = o.product_id
    
where date_format(o.produce_date, '%Y-%m') = '2022-05'

group by p.product_id
order by total_sales desc, p.product_id;

# where product_date 2022년 5월

# 20:16 