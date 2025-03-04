import java.util.*;

class Solution {
    
    // terms(약관종류 유효기간) <= 20
    // privacies(날짜 약관종류) <= 100
    public int[] solution(String today, String[] terms, String[] privacies) {
        int convertedToday = convertDate(today);
        
        // terms map으로 저장
        Map<Character, Integer> termMap = new HashMap<>();
        for (int i = 0; i < terms.length; i++) {
            StringTokenizer st = new StringTokenizer(terms[i]);
            
            char termType = st.nextToken().toCharArray()[0];
            int date = Integer.parseInt(st.nextToken()) * 28;
            
            termMap.put(termType, date);
        }
        
        List<Integer> answerList = new ArrayList<>();
        // privacies 순회: O(100)
        for (int i = 0; i < privacies.length; i++) {
            // 개인정보 파기 일자 구하기
            StringTokenizer st = new StringTokenizer(privacies[i]);
            int createDate = convertDate(st.nextToken());
            char termType = st.nextToken().toCharArray()[0];
            
            // today >= 파기일자 이면 answer 추가            
            int expiredDate = createDate + termMap.get(termType);
            if (convertedToday >= expiredDate) {
                answerList.add(i + 1);
            }
        }
        
        return answerList.stream().mapToInt(a -> a).toArray();
    }
    
    // 날짜 변화 ((년도-2000)*28*13 + 월*28 + 일자 - 1)
    private int convertDate(String today) {
        StringTokenizer st = new StringTokenizer(today, ".");
        
        int convertedToday = 0;
        convertedToday += ((Integer.parseInt(st.nextToken()) - 2000) * 28 * 12);
        convertedToday += (Integer.parseInt(st.nextToken()) * 28);
        convertedToday += Integer.parseInt(st.nextToken()) - 1;
        
        return convertedToday;
    }
}

// 파기해야할 정보 오름차순으로 리턴