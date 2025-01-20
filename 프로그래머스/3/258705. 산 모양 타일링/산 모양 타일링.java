class Solution {
    private static final int DIVISOR = 10007;
    
    public int solution(int n, int[] tops) {    
        // dp 초기값 설정
        int[][] dp = new int[n][2];
        dp[0][0] = 1;
        if (tops[0] == 0) {
            dp[0][1] = 2;
        } else{
            dp[0][1] = 3;
        }
        
        // tops 반복하며 dp[n-1] 까지 채우기 (% 10007)
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i-1][0] + dp[i-1][1];
            if (tops[i] == 0) {
                dp[i][1] = dp[i-1][0] + 2 * dp[i-1][1];
            } else {
                dp[i][1] = 2 * dp[i-1][0] + 3 * dp[i-1][1];
            }
            
            dp[i][0] %= DIVISOR;
            dp[i][1] %= DIVISOR;
        }
        return (dp[n-1][0] + dp [n-1][1]) % DIVISOR;
    }
}

// 10:28

// 2차원 dp: O(n)
// tops와 침범 여부에 따라 나누기
