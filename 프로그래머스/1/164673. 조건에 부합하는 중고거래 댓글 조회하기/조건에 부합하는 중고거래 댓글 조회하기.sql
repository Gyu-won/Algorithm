-- 코드를 입력하세요
SELECT board.title, board.board_id, reply.reply_id, reply.writer_id, 
    reply.contents, date_format(reply.created_date, '%Y-%m-%d') as created_date
from used_goods_board as board, used_goods_reply as reply
where board.board_id = reply.board_id and 
    year(board.created_date) = 2022 and month(board.created_date) = 10 
order by reply.created_date, board.title;