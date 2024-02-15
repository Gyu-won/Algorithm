import java.util.*;

class Solution {
    
    private static final int[] dr = new int[]{0, 1, 0, -1};
    private static final int[] dc = new int[]{1, 0, -1, 0};
    
    public int solution(int[][] board) {
        // dp 선언
        int n = board.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[0][0] = 0;
        
        boolean[][] visited = new boolean[n][n];
        visited[0][0] = true;
        
        // pq 선언
        PriorityQueue<int[]> pq = new PriorityQueue<>(new RoadComparator());
        
        // 오, 아래 확인해서 넣기
        if (board[0][1] == 0){
            pq.offer(new int[]{0, 1, 100, 0});
            dp[0][1] = 100;
        }
        if (board[1][0] == 0){
            pq.offer(new int[]{1, 0, 100, 1});
            dp[1][0] = 100;
        }        
        
        
        while(!pq.isEmpty() && pq.peek()[2] <= dp[n-1][n-1]){
            int[] node = pq.poll();
            dp[node[0]][node[1]] = Math.min(dp[node[0]][node[1]], node[2]);
            for (int d = 0; d < 4; d++){
                int r = node[0] + dr[d];
                int c = node[1] + dc[d];

                if (r >= 0 && r < n && c >= 0 && c < n && board[r][c] == 0){
                    // 4방향 중에서 갈 수 있는것만 금액 계산해서 pq에 넣기(i, j, p, d)
                    int p = node[2] + 100;
                    if (node[3] != d){
                        p += 500;
                    }
                        
                    if (p-500 <= dp[r][c]){
                        pq.offer(new int[]{r, c, p, d});                            
                    }                         
                }
            }
        }
    
        return dp[n-1][n-1];
    }
    
    static class RoadComparator implements Comparator<int[]> {
        @Override
        public int compare (int[] r1, int[] r2){
            return r1[2] - r2[2];
        }
    }
}

// 요약
// nxn 도면, 0은 비어있음, 1은 벽임을 나타냄 (n: 3-25)
// 0, 0에서 n-1, n-1로 가는데 안끊겨야함
// 4방향으로 건설 가능하고, 벽이 있으면 불가
// 직선도로는 100원, 코너는 500원이 드는데 최소 비용 계산



// O()

// 21:50 - 

