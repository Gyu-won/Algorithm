-- 코드를 입력하세요
SELECT u.user_id, u.nickname, b.total_sales
from used_goods_user u join (
    select writer_id, sum(price) as total_sales
    from used_goods_board
    where status = 'done'
    group by writer_id
    having sum(price) >= 700000) b on u.user_id = b.writer_id
order by total_sales;

# status가 done인 중고거래 에 대한 사람별 price 합이 700,000 이상 인 사람을 조회

