-- 코드를 입력하세요
SELECT fh.flavor
from first_half as fh, icecream_info as ii
where fh.flavor = ii.flavor and fh.total_order > 3000 and ingredient_type = 'fruit_based'
order by total_order desc;