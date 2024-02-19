-- 코드를 입력하세요
SELECT year(sale.sales_date) as year, 
    month(sale.sales_date) as month,
    info.gender as gender,
    count(distinct(info.user_id)) as users
    

from online_sale sale
    join user_info info
    on sale.user_id = info.user_id

where info.gender is not null

group by year(sale.sales_date), month(sale.sales_date), info.gender

order by year, month, gender;
 