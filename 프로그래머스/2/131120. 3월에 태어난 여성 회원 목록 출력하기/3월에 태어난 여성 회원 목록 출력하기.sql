SELECT MEMBER_ID, MEMBER_NAME, GENDER, date_format(DATE_OF_BIRTH, '%Y-%m-%d') as DATE_OF_BIRTH
from MEMBER_PROFILE
where date_format(DATE_OF_BIRTH, '%m') = 03 and TLNO is not NULL and GENDER = 'W'
order by MEMBER_ID asc