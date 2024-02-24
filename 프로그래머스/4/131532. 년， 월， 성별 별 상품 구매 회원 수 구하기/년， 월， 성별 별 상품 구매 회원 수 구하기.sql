
select year(sale.sales_date) as year, month(sale.sales_date) as month, info.gender, count(distinct(info.user_id)) as users
from user_info info
    join online_sale sale
    on info.user_id = sale.user_id
where gender is not null
group by year(sale.sales_date), month(sale.sales_date), info.gender
order by year(sale.sales_date), month(sale.sales_date), info.gender
# gender 0 남자 1 여자