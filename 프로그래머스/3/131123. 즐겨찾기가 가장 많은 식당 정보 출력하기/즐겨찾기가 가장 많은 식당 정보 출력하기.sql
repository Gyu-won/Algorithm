-- 코드를 입력하세요
# SELECT food_type, rest_id, rest_name, favorites
# from rest_info
# group by food_type
# having favorites = max(favorites)
# order by food_type desc;

# SELECT food_type, rest_id, rest_name, favorites
# from rest_info

select r.food_type, r.rest_id, r.rest_name, r.favorites
from rest_info r right outer join (select food_type, max(favorites) as count
    from rest_info
    group by food_type) a
    on r.food_type = a.food_type and r.favorites = a.count
order by r.food_type desc;