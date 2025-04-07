import java.util.*;

class Solution {
    private static final int[] dr = new int[]{1, 0, -1};
    private static final int[] dc = new int[]{0, 1, -1};

    private static int n;
    private static int[][] board;
    
    public int[] solution(int input) {
        // 배열 설정
        n = input;
        board = new int[n][n];
        
        // 값 채우기
        fillBoard();
        
        // 출력
        return printAnswer();
    }
    
    private void fillBoard() {
        int d = 0, value = 1;
        int r = -1, c = 0;
        
        while(true) {
            // 방향대로 가보고 
            int nextR = r + dr[d];
            int nextC = c + dc[d];
            
            if (isValid(nextR, nextC)) {
                // 0이면 값 채우기
                board[nextR][nextC] = value;
                value++;
                r = nextR;
                c = nextC;
            } else{
                // 0아니면 방향 ++ 후 값 채우기 (0아니면 종료)
                d = (d+1) % 3;
                nextR = r + dr[d];
                nextC = c + dc[d];
                
                if (isValid(nextR, nextC)) {
                   board[nextR][nextC] = value;
                    value++;
                    r = nextR;
                    c = nextC;
                } else {
                    break;
                }
            }
        }
    }
    
    private boolean isValid(int r, int c) {
        return r < n && c < n && board[r][c] == 0;
    }
    
    private int[] printAnswer() {
        int[] answer = new int[(n * (n+1)) / 2];
        int i = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c <= r; c++) {
                answer[i++] = board[r][c];
            }
        }
        return answer;
    }
}

// 1000x1000