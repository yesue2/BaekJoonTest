-- 코드를 입력하세요
select BOOK_ID, date_format(PUBLISHED_DATE, '%Y-%m-%d')
from BOOK
where PUBLISHED_DATE between '2021-01-01' and '2021-12-31' and CATEGORY="인문"
order by PUBLISHED_DATE asc;