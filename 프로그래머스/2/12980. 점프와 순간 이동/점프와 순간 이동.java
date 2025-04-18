import java.util.*;

public class Solution {
    public int solution(int n) {
        String binaryString = Integer.toBinaryString(n);
        
        int count = 0;
        for (char ch: binaryString.toCharArray()) {
            if (ch == '1') {
                count++;
            }
        }
        return count;
    }
}