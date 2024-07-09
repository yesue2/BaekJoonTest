SELECT info.REST_ID, info.REST_NAME, info.FOOD_TYPE, info.FAVORITES, info.ADDRESS, round(avg(rev.REVIEW_SCORE), 2) as SCORE
from REST_REVIEW as rev join REST_INFO as info on info.REST_ID = rev.REST_ID
where info.ADDRESS like '서울%'
group by info.REST_ID, info.REST_NAME, info.FOOD_TYPE, info.FAVORITES, info.ADDRESS
ORDER BY SCORE desc, info.FAVORITES desc