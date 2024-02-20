class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        
        int n = lock.length;
        int m = key.length;
        
        // key 회전하기 O(4)
        for (int r = 0; r < 4; r++){
            key = rotate(key, m);
            
            // key 시작 위치 변경
            int boardSize = n + m - 1 + m - 1;
            for (int startR = 0; startR < boardSize - m + 1; startR++){
                for (int startC = 0; startC < boardSize - m + 1; startC++){
                    // board 만들기
                    int[][] board = createBoard(key, startR, startC, boardSize);
                    
                    // 확인
                    if (isOpen(n, m, lock, board)){
                        return true;
                    }
                }
            }
        }
            
        return false;
    }
    
    private boolean isOpen(int n, int m, int[][] lock, int[][] board){
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (lock[i][j] == board[i+m-1][j+m-1]){
                    return false;
                }
            }
        }           
        return true;
    }
    
    private int[][] createBoard(int[][] key, int startR, int startC, int size){
        int[][] board = new int[size][size];
        int keyLen = key.length;
        for (int i = 0; i < keyLen; i++){
            for (int j = 0; j < keyLen; j++){
                board[i+startR][j+startC] = key[i][j];
            }
        }
        return board;
    }
    
    
    private int[][] rotate(int[][] key, int m){
        int[][] rotatedKey = new int[m][m];
        
        for (int i = 0; i < m; i++){
            for (int j = 0; j < m; j++){
                rotatedKey[j][m-1-i] = key[i][j];
            }
        }
        
        return rotatedKey;
    }
}

// 요약
// nxn 격자, 열쇠는 mxm 격자, 자물쇠 홈이 모두 채워져야 열림 (m <= n, 3-20)
// 0은 홈, 1은 돌기

// O()

// 1500 -