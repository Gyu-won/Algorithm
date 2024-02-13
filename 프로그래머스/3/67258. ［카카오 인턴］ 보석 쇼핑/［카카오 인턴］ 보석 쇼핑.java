import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        
        // 보석 종류 찾기 O(100,000) map으로 저장
        Set<String> gemTypes = new HashSet<>();
        for (String gem: gems){
            gemTypes.add(gem);
        }
        
        int gemType = gemTypes.size();
        int start = 0, end = 0;
        
        int cnt = 0;
        int minSize = Integer.MAX_VALUE;
        Map<String, Integer> map = new HashMap<>();
        // 투포인터로 보석이 없으면 end를, 있으면 start 를 줄이기
        while (end <= gems.length){
            if (cnt < gemType){
                if (end == gems.length){
                    break;
                }
                int count = map.getOrDefault(gems[end], 0);
                if (count == 0){
                    cnt++;
                }
                map.put(gems[end], count+1);
                end++;
            }else {
                if (cnt == gemType && end - start < minSize){
                    minSize = end - start;
                    answer[0] = start + 1;
                    answer[1] = end;
                }
                int count = map.get(gems[start]) - 1;
                if (count == 0){
                    cnt--;
                }
                map.put(gems[start], count);
                start++;
            }
        }
        // 줄이면서 minSize를 update, update 할 때 시작 끝 점도 저장
        return answer;
    }
}

// 요약
// 모든 종류의 보석을 1개 이상 포함하는 가장 짧은 연속 구간
// 가장 짧은 구간의 시작과 끝을 리턴, 여러개면 가장 작은거를 리턴
// gems 크기 (1-100000) (1부터 시작)



// 14:49 - 

// O()