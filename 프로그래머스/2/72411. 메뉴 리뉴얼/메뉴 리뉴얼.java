import java.util.*;

class Solution {
    
    private static Map<String, Integer> map;
    
    public String[] solution(String[] orders, int[] course) {
        List<String> results = new ArrayList<>();
        
        
        // course 크기에 따라서 가능한 모든 조합 구하기 (hashMap)
        for (int size: course){
            map = new HashMap<>();
            
            for (String menu: orders){
                char[] menus = menu.toCharArray();
                Arrays.sort(menus);
                findComb(size, 0, 0, "", menus);
            }
            
            // 조합 순회해서 2보다 큰거 결과에 추가
            int maxValue = 0;
            for (String s: map.keySet()){
                maxValue = Math.max(map.get(s), maxValue);
            }            
            if (maxValue >= 2){
                for (String s: map.keySet()){
                    if (map.get(s) == maxValue){
                        results.add(s);
                    }
                }
            }
        }
        
        // 전체 결과 오름차순 정렬
        Collections.sort(results);
        return results.toArray(new String[results.size()]);
    }
    
    private void findComb(int size, int start, int current, String comb, char[] menus){
        if (size == current){
            int count = map.getOrDefault(comb, 0);
            map.put(comb, count + 1);
            return;
        }
        for (int i = start; i < menus.length; i++){
            findComb(size, i+1, current + 1, comb + menus[i], menus);
        }
        
    }
}

// orders 크기 2-10, order는 (2-10), 중복 x
// course 크기는 1-10 (2-10) 이하 자여수 오름차순 정렬, 중복 x
// 정답은 오름차순 정렬


// 문제 요약
// 메뉴를 조합해서 코스요리로 (2가지 이상 포함)
// 주문량 2이상의 메뉴만 코스메뉴 후보에 포함
// 메뉴는 A-Z


// 12:08 - 

// O()