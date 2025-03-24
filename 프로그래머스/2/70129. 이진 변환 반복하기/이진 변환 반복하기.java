import java.util.*;

class Solution {
    // s 길이 1~15*10^4
    public int[] solution(String s) {
        int[] answer = new int[] {0, 0};
        
        while(!s.equals("1")) {
            answer[0]++;
            int prevLength = s.length();
            s = s.replaceAll("0", "");
            answer[1] += prevLength - s.length();
            s = Integer.toString(s.length(), 2);
        }
        
        return answer;
    }
}

// 이진 변환: s가 "1"될 때까지
// 1개수 구하기 (하나씩 보면서 탐색)
// count++;
// 0 개수 반영
// s 변경