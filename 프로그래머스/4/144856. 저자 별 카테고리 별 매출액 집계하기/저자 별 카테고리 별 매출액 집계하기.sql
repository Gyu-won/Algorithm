select book.author_id, author.author_name, book.category, 
    sum(book_sales.sales * book.price) as sales
from book
    join author
    on book.author_id = author.author_id
    
    join book_sales
    on book.book_id = book_sales.book_id

where date_format(book_sales.sales_date, '%Y-%m') = '2022-01'
group by book.author_id, book.category
order by book.author_id, book.category desc;