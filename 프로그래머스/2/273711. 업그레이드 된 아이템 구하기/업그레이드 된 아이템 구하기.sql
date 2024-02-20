-- 코드를 작성해주세요

select info.item_id, info.item_name, info.rarity
from item_tree tree
    join item_info info
    on tree.item_id = info.item_id

where tree.parent_item_id in (select item_id
                        from item_info
                        where rarity = 'rare')
                        
order by info.item_id desc;