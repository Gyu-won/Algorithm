class Solution {
    
    private int[] result = new int[]{0, 0};
    private int[][] board;
    private int[][] sum;
    
    // arr <= 1024x1024
    public int[] solution(int[][] arr) {
        int n = arr.length;
        board = arr;
        
        // 부분 합 만들기
        sum = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + arr[i-1][j-1];
            }
        }
        
        
        dfs(1, 1, n, n);
        return result;
    }
    
    private void dfs(int sr, int sc, int er, int ec) {
        int size = (er - sr + 1) * (ec - sc + 1);
        if (size == 1) {
            result[board[er - 1][ec - 1]]++;
            return;
        }
        
        int numberOfOne = sum[er][ec] - sum[er][sc-1] - sum[sr-1][ec] + sum[sr-1][sc-1];
        
        if (numberOfOne == size) {
            result[1]++;
            return;
        }
        if (numberOfOne == 0) {
            result[0]++;
            return;
        }
        int midR = (sr + er) / 2;
        int midC = (sc + ec) / 2;
        dfs(sr, sc, midR, midC);
        dfs(sr, midC + 1, midR, ec);
        dfs(midR + 1, sc, er, midC);
        dfs(midR + 1, midC + 1, er, ec);
    }
}

// 최악의 경우: 1 + 2^2 + 2^4 +... + 2^10: O(2^12)
// 다 같은 수인지 검사 (sr, sc, er, ec)
    // 넓이 1인 경우 그냥 그 숫자 더하기
    // 넓이 계산: 부분합 O(1)
    // 넓이랑 숫자 합 같으면 1개수 ++
    // 넓이가 0이면 0개수 ++
// 다르면 재귀로 나누기