
# select item_id, item_name, rarity

# order by item_id;
# 희귀도 rare인 아이템
# child 아이템 

select item_id, item_name, rarity
from item_info
where item_id in (select tree.item_id
                from item_info info
                    join item_tree tree
                    on info.item_id = tree.parent_item_id
                where info.rarity = 'RARE')
order by item_id desc;