SELECT board.TITLE, 
board.BOARD_ID, 
REPLY_ID, 
reply.WRITER_ID, 
reply.CONTENTS, 
date_format(reply.CREATED_DATE, '%Y-%m-%d') as CREATED_DATE
from USED_GOODS_BOARD as board 
join USED_GOODS_REPLY as reply 
on board.BOARD_ID = reply.BOARD_ID
where date_format(board.CREATED_DATE, '%Y-%m-%d') >= '2022-10-01' and date_format(board.CREATED_DATE, '%Y-%m-%d') <= '2022-10-31'
order by reply.CREATED_DATE asc, board.TITLE asc
