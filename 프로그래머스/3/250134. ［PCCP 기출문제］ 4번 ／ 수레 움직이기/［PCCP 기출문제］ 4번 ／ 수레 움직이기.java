class Solution {
    
    private static final int[] dr = new int[]{-1, 0, 1, 0};
    private static final int[] dc = new int[]{0, 1, 0, -1};
    
    private static int n, m, minTime = Integer.MAX_VALUE;
    private static int[] redDestination = new int[2];
    private static int[] blueDestination = new int[2];
    private static int[][] map;
    private static boolean[][] redVisited;
    private static boolean[][] blueVisited;
    
    public int solution(int[][] maze) {
        map = maze;
        
        n = map.length;
        m = map[0].length;
        
        redVisited = new boolean[n][m];
        blueVisited = new boolean[n][m];

        int[] redStart = new int[2];
        int[] blueStart = new int[2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1) {
                    redStart[0] = i;
                    redStart[1] = j;
                } else if (map[i][j] == 2) {
                    blueStart[0] = i;
                    blueStart[1] = j;
                } else if (map[i][j] == 3) {
                    redDestination[0] = i;
                    redDestination[1] = j;
                } else if (map[i][j] == 4) {
                    blueDestination[0] = i;
                    blueDestination[1] = j;
                }
            }
        }
        
        redVisited[redStart[0]][redStart[1]] = true;
        blueVisited[blueStart[0]][blueStart[1]] = true;
        dfs(redStart[0], redStart[1], blueStart[0], blueStart[1], 0);
        
        if (minTime == Integer.MAX_VALUE) {
            return 0;
        }
        return minTime;
    }
    
    // 중첩 dfs
    private static void dfs(int redR, int redC, int blueR, int blueC, int time) {
        if (redComplete(redR, redC) && blueComplete(blueR, blueC)) {
            minTime = Math.min(time, minTime);
            return;
        }
        if (redComplete(redR, redC)) {
            for (int d = 0; d < 4; d++) {
                int nextBlueR = blueR + dr[d];
                int nextBlueC = blueC + dc[d];
                
                if (isValid(nextBlueR, nextBlueC) && !blueVisited[nextBlueR][nextBlueC] && 
                   !(redR == nextBlueR && redC == nextBlueC)) {
                    blueVisited[nextBlueR][nextBlueC] = true;
                    dfs(redR, redC, nextBlueR, nextBlueC, time + 1);
                    blueVisited[nextBlueR][nextBlueC] = false;
                }
            }
        } else if (blueComplete(blueR, blueC)) {
            for (int d = 0; d < 4; d++) {
                int nextRedR = redR + dr[d];
                int nextRedC = redC + dc[d];
                
                if (isValid(nextRedR, nextRedC) && !redVisited[nextRedR][nextRedC] &&
                   !(nextRedR == blueR && nextRedC == blueC)) {
                    redVisited[nextRedR][nextRedC] = true;
                    dfs(nextRedR, nextRedC, blueR, blueC, time + 1);
                    redVisited[nextRedR][nextRedC] = false;
                }
            }
        } else{
            for (int redD = 0; redD < 4; redD++) {
                int nextRedR = redR + dr[redD];
                int nextRedC = redC + dc[redD];
                
                if (isValid(nextRedR, nextRedC) && !redVisited[nextRedR][nextRedC]) {
                    redVisited[nextRedR][nextRedC] = true;
            
                    for (int blueD = 0; blueD < 4; blueD++) {
                        int nextBlueR = blueR + dr[blueD];
                        int nextBlueC = blueC + dc[blueD];

                        if (isValid(nextBlueR, nextBlueC) && !blueVisited[nextBlueR][nextBlueC]
                           && !(redR == nextBlueR && redC == nextBlueC && nextRedR == blueR && nextRedC == blueC) && !(nextRedR == nextBlueR && nextRedC == nextBlueC)) {
                            blueVisited[nextBlueR][nextBlueC] = true;
                            dfs(nextRedR, nextRedC, nextBlueR, nextBlueC, time + 1);
                            blueVisited[nextBlueR][nextBlueC] = false;
                        }
                    }
                    redVisited[nextRedR][nextRedC] = false;
                }
            }
        }
    }
            
    private static boolean redComplete(int r, int c) {
        return r == redDestination[0] && c == redDestination[1];
    }
            
    private static boolean blueComplete(int r, int c) {
        return r == blueDestination[0] && c == blueDestination[1];
    }
    
    private static boolean isValid(int r, int c) {
        // 유효성 체크, 벽인지 체크
        return r >= 0 && r < n && c >= 0 && c < m && map[r][c] != 5;
    }
        // 둘 다 도착
        // 빨간색 도착
        // 파란색 도착
        // 둘다 도착 안함
}

// dfs 2개가 중첩
