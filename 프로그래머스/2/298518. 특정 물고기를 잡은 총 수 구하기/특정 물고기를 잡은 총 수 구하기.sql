select count(id) as fish_count
from fish_info 
join fish_name_info on fish_info.fish_type = fish_name_info.fish_type
where fish_name='BASS' or fish_name='SNAPPER'