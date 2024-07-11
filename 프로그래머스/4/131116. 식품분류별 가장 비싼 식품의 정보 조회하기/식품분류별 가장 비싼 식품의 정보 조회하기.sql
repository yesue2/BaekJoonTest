SELECT food.CATEGORY, food.PRICE as MAX_PRICE, food.PRODUCT_NAME
from FOOD_PRODUCT as food
inner join (
    select CATEGORY, max(PRICE) as MAX_PRICE
    from FOOD_PRODUCT
    where CATEGORY in ('과자', '국', '김치', '식용유')
    group by CATEGORY
) MAX_PRICES on food.CATEGORY = MAX_PRICES.CATEGORY and food.PRICE = MAX_PRICES.MAX_PRICE
order by food.PRICE desc;
