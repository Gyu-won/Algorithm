
import java.util.*;

class Solution {
    // s 길이 1~15*10^4
    public int[] solution(String s) {
        int[] answer = new int[] {0, 0};

        while(!s.equals("1")) {
            answer[0]++;

            int oneCount = 0;
            for (char ch: s.toCharArray()) {
                if (ch == '1') {
                    oneCount++;
                }
            }

            answer[1] += s.length() - oneCount;
            s = Integer.toString(oneCount, 2);
        }

        return answer;
    }
}