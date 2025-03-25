import java.util.*;

class Solution {
    
    // s길이<=10^6, s의 각 원소 길이<=10^6, 모든 원소 길이 합<=10^6
    public String[] solution(String[] s) {
        int len = s.length;
        String[] answer = new String[len];
        for (int i = 0; i < len; i++) {
            answer[i] = reArrange(s[i]);
        }
        return answer;
    }
    
    private String reArrange(String s) {
        int n = s.length();
        if (n <= 3) {
            return s;
        }
        
        // 110 개수 세기
        int count = 0;
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == '0' && deque.size() > 1) {
                char ch2 = deque.removeLast();
                char ch1 = deque.removeLast();
                
                if (ch2 == '1' && ch1 == '1') {
                    count++;
                    continue;
                }
                deque.addLast(ch1);
                deque.addLast(ch2);
            }
            deque.addLast(ch);
        }
        
        StringBuilder tempStr = new StringBuilder();
        while (!deque.isEmpty()) {
            tempStr.append(deque.removeFirst());
        }
        
        StringBuilder answer = new StringBuilder();
        String str = tempStr.toString();
        int idx = str.lastIndexOf('0');
        if (idx == -1) {
            for (int i = 0; i < count; i++) {
                answer.append("110");
            }
             return answer.append(str).toString();
        } 
            answer.append(str.substring(0, idx + 1));
            for (int i = 0; i < count; i++) {
                answer.append("110");
            }
            return answer.append(str.substring(idx + 1, str.length())).toString();
        
//         if (deque.size() < 3) {
//             while(!deque.isEmpty()) {
//                 if (deque.getFirst() == '1') {
//                     for (int i = 0; i < count; i++) {
//                         result.append("110");    
//                     }
//                     count = 0;
//                 }
//                 result.append(deque.removeFirst());
//             }
//             for (int i = 0; i < count; i++) {
//                 result.append("110");    
//             }
//             return result.toString();
//         }

//         char ch1 = deque.removeFirst();
//         char ch2 = deque.removeFirst();
//         while(!deque.isEmpty()) {
//             if (ch1 == '1' && ch2 == '1' && deque.getFirst() == '1') {
//                 deque.addFirst(ch2);
//                 deque.addFirst(ch1);
//                 break;
//             }
//             result.append(ch1);
//             ch1 = ch2;
//             ch2 = deque.removeFirst();
//         }
        
//         if (deque.size() > 0) {
//             for (int i = 0; i < count; i++) {
//                 result.append("110");    
//             }
//             while(!deque.isEmpty()) {
//                 result.append(deque.removeFirst());
//             }
//         } else {
//             if (ch2 == '1') {
//                 if (ch1 == '1') {
//                     for (int i = 0; i < count; i++) {
//                        result.append("110");    
//                     }
//                     result.append(ch1);
//                     result.append(ch2);
//                 } else {
//                     result.append(ch1);
//                     for (int i = 0; i < count; i++) {
//                        result.append("110");    
//                     }
//                     result.append(ch2);
//                 }
//             } else {
//                 result.append(ch1);
//                 result.append(ch2);
//                 for (int i = 0; i < count; i++) {
//                     result.append("110");    
//                 }
//             }
//         }
//         return result.toString();
    }
}

// 110 다 빼기 O(s)
// 111 위치 구하기 -> 앞에 추가 O(s)
// 없으면 뒤에서 부터 1인것까지 올라가기 -> 앞에 추가