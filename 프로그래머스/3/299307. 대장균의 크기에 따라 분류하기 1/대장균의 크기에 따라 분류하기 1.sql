select id, case 
when size_of_colony <= 100 then 'LOW'
when size_of_colony <= 1000 and size_of_colony > 100 then 'MEDIUM'
else 'HIGH'
end as size
from ecoli_data
order by id