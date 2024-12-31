import java.util.*;

class Solution {

    private static final int DIVISOR = 10007;
    public int solution(int n, int[] tops) {
        int answer = 0;
        
        int[] effect = new int[n];
        int[] noEffect = new int[n];
        
        effect[0] = 1;
        noEffect[0] = 2;
        if (tops[0] == 1) {
            noEffect[0]++;
        }
        
        for (int i = 1; i < n; i++) {
            effect[i] = (effect[i-1] + noEffect[i-1]) % DIVISOR;
            if (tops[i] == 0) {
                noEffect[i] = effect[i-1] + 2 * noEffect[i-1];
            } else {
                noEffect[i] = 2 * effect[i-1] + 3 * noEffect[i-1];
            }
            noEffect[i] %= DIVISOR;
        }
        
        return (effect[n-1] + noEffect[n-1]) % DIVISOR;
    }
}

// dp 0 값 초기화
// 2부터 n 까지 늘려가며 값 채우기
// 경우의 수를 모두 나누어서 dp로 계산

// 16:20