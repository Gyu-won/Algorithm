class Solution {
    
    private int[][] board;
    
    public int[] solution(int rows, int columns, int[][] queries) {
        board = new int[rows + 1][columns + 1];
        for (int r = 1; r <= rows; r++) {
            for (int c = 1; c <= columns; c++) {
                board[r][c] = ((r-1) * columns) + c;
            }
        }
        
        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            answer[i] = rotate(queries[i][0], queries[i][1], queries[i][2], queries[i][3]);   
        }
        return answer;
    }
    
    private int rotate(int minR, int minC, int maxR, int maxC) {
        int temp = board[minR][minC];
        int minValue = temp;
        for (int r = minR; r < maxR; r++) {
            board[r][minC] = board[r+1][minC];
            minValue = Math.min(minValue, board[r][minC]);
        }
        for (int c = minC; c < maxC; c++) {
            board[maxR][c] = board[maxR][c+1];
            minValue = Math.min(minValue, board[maxR][c]);
        }
        for (int r = maxR; r > minR; r--) {
            board[r][maxC] = board[r-1][maxC];
            minValue = Math.min(minValue, board[r][maxC]);
        }
        for (int c = maxC; c > minC + 1; c--) {
            board[minR][c] = board[minR][c-1];
            minValue = Math.min(minValue, board[minR][c]);
        }
        board[minR][minC+1] = temp;
        return minValue;
    }
}