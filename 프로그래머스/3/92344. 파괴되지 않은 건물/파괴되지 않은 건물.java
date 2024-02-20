class Solution {
    public int solution(int[][] board, int[][] skill) {
        
        int n = board.length;
        int m = board[0].length;
        
        int[][] deriv = new int[n+1][m+1];
        
        // skill 읽기
        for (int skillIdx = 0; skillIdx < skill.length; skillIdx++){
            int[] skillInfo = skill[skillIdx];
            if (skillInfo[0] == 1){
                // 공격
                calculateDeriv(skillInfo[1], skillInfo[2], skillInfo[3], skillInfo[4], skillInfo[5] * -1, deriv);
            }else{
                // 회복
                calculateDeriv(skillInfo[1], skillInfo[2], skillInfo[3], skillInfo[4], skillInfo[5], deriv);
            }
        }
        
        // 누적합 계산하기
        calculateSum(n, m, deriv);
        
        // board에 더하기
        addDerivToBoard(n, m, deriv, board);
        
            
        // 개수 구하기    
        return countLiveBuilding(n, m, board);
    }
    
    private int countLiveBuilding(int n, int m, int[][] board){
        int count = 0;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if (board[i][j] > 0){
                    count++;
                }
            }
        }
        return count;
    }
    
    private void calculateSum(int n, int m, int[][] deriv){
        for (int j = 0; j < m; j++){
            for (int i = 1; i < n; i++){
                deriv[i][j] += deriv[i-1][j];
            }
        }
        
        for (int i = 0; i < n; i++){
            for (int j = 1; j < m; j++){
                deriv[i][j] += deriv[i][j-1];
            }
        }
    }
    
    private void addDerivToBoard(int n, int m, int[][] deriv, int[][] board){
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                board[i][j] += deriv[i][j];
            }
        }
    }
    
    private void calculateDeriv(int r1, int c1, int r2, int c2, int degree, int[][] deriv){
        deriv[r1][c1] += degree;
        deriv[r1][c2+1] -= degree;
        deriv[r2+1][c1] -= degree;
        deriv[r2+1][c2+1] += degree;
    }
}

// 요약
// nxm 크기 지도, 0이면 파괴 (n: 1-1000, m: 1-1000) 내구도 (1-1000)
// skill (1-250,000, 6)
// type, r1, c1, r2, c2, degree / 1이면 공격, 2이면 회복 / degree(1-500)

//공격
// 시작점 끝점으로 직사각형 공격, 내구도 - (끝점 포함)

// 회복
// 시작점 끝점으로 직사각형 회복, 내구도 + (끝점 포함)

// skill 모두 끝나고 파괴되지 않은 건물 개수 구하기

// 완탐풀자

// O(n*m*skill)

// 16:29 - 