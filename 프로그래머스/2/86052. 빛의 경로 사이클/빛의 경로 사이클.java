import java.util.*;

class Solution {
    
    private final int[] dr = new int[] {0, 1, 0, -1};
    private final int[] dc = new int[] {1, 0, -1, 0};
    
    private int n, m;
    private char[][] board;
    private boolean[][][] visited;
    
    // <= 500*500
    public int[] solution(String[] grid) {
        n = grid.length;
        m = grid[0].length();
        
        board = new char[n][m];
        visited = new boolean[n][m][4];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = grid[i].charAt(j);
            }
        }
        
        List<Integer> result = new ArrayList<>();
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                for (int d = 0; d < 4; d++) {
                    if (!visited[r][c][d]) {
                        result.add(countCycleLength(r, c, d));
                    }
                }
            }
        }
        Collections.sort(result);
        return result.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
    
    private int countCycleLength(int r, int c, int d) {
        int count = 0;
        
        while(!visited[r][c][d]) {
            count++;
            int[] nextPos = move(r, c, d);
            r = nextPos[0];
            c = nextPos[1];
            d = nextPos[2];
        }
        return count;
    }
    
    private int[] move(int r, int c, int d) {
        int nextR = 0, nextC = 0, nextD = 0;
        
        visited[r][c][d] = true;
        if (board[r][c] == 'S') {
            nextD = d;    
        } else if (board[r][c] == 'L') {
            nextD = (d + 3) % 4;
        } else {
            nextD = (d + 1) % 4;
        }
        nextR = (r + dr[nextD] + n) % n;
        nextC = (c + dc[nextD] + m) % m;
        return new int[] {nextR, nextC, nextD};
    }
}

// 모든 경우 탐색: 500 * 500 * 4 = O(10^6)
// 정렬: O(nlogn)

// 각 길이 구하는 것: 시작했으면 다시 중복될 때까지 구하기
// 이동:
    // nextR, nextC 먼저 계산
    // nextR 범위 확인 후 바꾸기
    // nextC 범위 확인 후 바꾸기