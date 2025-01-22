import java.util.*;

class Solution {
    
    private static final int[] dr = new int[] {0, -1, 0, 1};    
    private static final int[] dc = new int[] {-1, 0, 1, 0};
    
    private static int n, m;
    private static boolean[][] visited;
    
    public int solution(int[][] land) {
        n = land.length;
        m = land[0].length;
        visited = new boolean[n][m];
        
        // oils[가로] 구하기: 
        int[] oilSum = calculateOilSum(land);
        
        // oil 부분합 순회하며 최대값 구하기 O(n)
        int maxOilQuantity = oilSum[0];
        for (int i = 1; i < m; i++) {
            oilSum[i] += oilSum[i-1];
            maxOilQuantity = Math.max(maxOilQuantity, oilSum[i]);
        }
        return maxOilQuantity;
    }
    
    private static int[] calculateOilSum(int[][] land) {
        int[] oilSum = new int[m+1];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (land[r][c] == 1 && !visited[r][c]) {
                    // bfs O(n * m)
                    int[] oilInfo = bfs(r, c, land);
                    
                    // oil[start]에 더하기, oil[end+1]에 빼기
                    oilSum[oilInfo[0]] += oilInfo[2];
                    oilSum[oilInfo[1] + 1] -= oilInfo[2];
                }
            }
        }
        return oilSum;
    }
    
    private static int[] bfs (int r, int c, int[][] land) { 
        int minC = m - 1;
        int maxC = 0;
        int quantity = 0;
        Deque<int[]> queue = new ArrayDeque<>();
        
        visited[r][c] = true;
        queue.addLast(new int[] {r, c});
                    
        while(!queue.isEmpty()) {
            int[] block = queue.removeFirst();
            quantity++;
            minC = Math.min(minC, block[1]);
            maxC = Math.max(maxC, block[1]);    
            
            for (int d = 0; d < 4; d++) {
                int nextR = block[0] + dr[d];
                int nextC = block[1] + dc[d];

                if (isValid(nextR, nextC) && land[nextR][nextC] == 1 && !visited[nextR][nextC]) {    
                    visited[nextR][nextC] = true;
                    queue.addLast(new int[] {nextR, nextC});
                }
            }
        }
        return new int[] {minC, maxC, quantity};
    }
    
    private static boolean isValid(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }
}

// O(n * n)