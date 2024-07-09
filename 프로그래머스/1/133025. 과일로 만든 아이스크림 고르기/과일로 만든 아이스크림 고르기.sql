SELECT info.FLAVOR
from FIRST_HALF as f join ICECREAM_INFO as info on f.FLAVOR = info.FLAVOR
where TOTAL_ORDER >= 3000 and INGREDIENT_TYPE = 'fruit_based'
order by TOTAL_ORDER desc