-- 코드를 입력하세요
select book_data.author_id, book_data.author_name, book_data.category, 
    sum(book_data.price * sale_data.sales) as total_sales
from (select b.book_id, a.author_id, a.author_name, b.category, b.price
    from book b
    join author a
    on b.author_id = a.author_id) book_data
    
    join (SELECT book_id, sum(sales) as sales
        from book_sales
        where date_format(sales_date, '%Y-%m') = '2022-01'
        group by book_id) sale_data
        
    on book_data.book_id = sale_data.book_id
group by book_data.author_id, book_data.category
order by book_data.author_id, book_data.category desc;
    



#2022년 1 판매 된거
# 저자별, 카테고리별 매출액

#16:59 - 