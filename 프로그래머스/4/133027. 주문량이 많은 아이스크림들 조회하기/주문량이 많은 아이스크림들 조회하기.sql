-- 코드를 입력하세요
SELECT fh.flavor

from first_half fh
    join (select flavor, sum(total_order) as orders
        from july
        group by flavor) july
    on fh.flavor = july.flavor

order by (fh.total_order + july.orders) desc
limit 3;




# 12:44