-- 코드를 입력하세요
SELECT f.category, fc.max_price, f.product_name
from food_product f
    
    join (select category, max(price) as max_price
        from food_product
        group by category) fc
    
    on f.category = fc.category
    
where f.category in ('과자', '국', '김치', '식용유')
    and
    f.price = fc.max_price

order by fc.max_price desc;


#category별 가격이 제일 비싼 식품의
# 분류, 가격, 이름

