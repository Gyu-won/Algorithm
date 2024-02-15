-- 코드를 입력하세요
SELECT substring(product_code, 1, 2) as category, count(*) as products
from product
group by substring(product_code, 1, 2)
order by category;
# 중복되지 않는 8자리 상품 코드, 앞에 2자리는 카테고리 코드