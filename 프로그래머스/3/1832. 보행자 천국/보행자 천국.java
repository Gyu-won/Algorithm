class Solution {
    int MOD = 20170805;
    public int solution(int n, int m, int[][] cityMap) {

        // dp 선언
        int[][] dp = new int[n][m];
                
        // dp 초기화
        for (int i = 1; i < n; i++){
            if (cityMap[i][0] == 1){
                 break;
            }
            dp[i][0] = 1;  
        }
        
        for (int j = 1; j < m; j++){
            if (cityMap[0][j] == 1){
                break;
            }
            dp[0][j] = 1;
        }
        
        // dp 채우기
        for (int i = 1; i < n; i++){
            for (int j = 1; j < m; j++){
                // map이 1이면 0
                if (cityMap[i][j] == 1){
                    dp[i][j] = 0;
                    continue;
                }
                
                // 기본적으로 dp[i][j] = dp[i-1][j] + dp[i][j-1]

                // 2가 아닐떄까지 가서 해당 값을 더해줌
                int r = i - 1;
                while (r >= 0 && cityMap[r][j] == 2) {
                    r--;
                }
                
                int c = j - 1;
                while (c >= 0 && cityMap[i][c] == 2) {
                    c--;
                }
                
                //범위 벗어나면 0임
                if (r >= 0){
                    dp[i][j] += dp[r][j];
                }
                if (c >= 0){
                    dp[i][j] += dp[i][c];
                }
                dp[i][j] %= MOD;
            }
        }
        
        return dp[n-1][m-1];
    }
}

// 250000 이기 때문에 dp로 구현
// 초기화 시 1인 칸은 dp를 0으로 나머지는 1로 초기화


// m x n map (1-500)
// 0 가능, 1 없음, 2 좌회전 우회전 금지
// 전체 가능한 경로 수를 나눈 값