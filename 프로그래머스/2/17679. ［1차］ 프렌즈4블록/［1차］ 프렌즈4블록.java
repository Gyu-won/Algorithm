class Solution {
    public int solution(int m, int n, String[] board) {
        int answer = m * n;

        char[][] originalBoard = new char[m][n];
        char[][] erasedBoard = new char[m][n];
        initializeBoard(board, originalBoard, erasedBoard);

        while (true) {
            findErasedBlock(originalBoard, erasedBoard);
            int count = findRemainedBlock(originalBoard, erasedBoard);
            if (count == answer) {
                break;
            }
            if (count < answer) {
                answer = count;
            }
        }

        System.out.println(m * n - answer);
        return m * n - answer;
    }

    private static void initializeBoard(String[] board, char[][] originalBoard, char[][] erasedBoard) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length(); j++) {
                originalBoard[i][j] = board[i].charAt(j);
                erasedBoard[i][j] = board[i].charAt(j);
            }
        }
    }

    private static int findRemainedBlock(char[][] originalBoard, char[][] erasedBoard) {
        int m = originalBoard.length;
        int n = originalBoard[0].length;

        int count = 0;
        for (int col = 0; col < n; col++) {
            StringBuilder lines = new StringBuilder();
            for (int row = 0; row < m; row++) {
                if (erasedBoard[m - 1 - row][col] != ' ') {
                    lines.append(erasedBoard[m - 1 - row][col]);
                    count++;
                }
            }
            for (int row = 0; row < lines.length(); row++) {
                originalBoard[m - 1 - row][col] = lines.charAt(row);
                erasedBoard[m - 1 - row][col] = lines.charAt(row);
            }
            for (int row = lines.length(); row < m; row++) {
                originalBoard[m - 1 - row][col] = ' ';
                erasedBoard[m - 1 - row][col] = ' ';
            }
        }
        return count;
    }

    private static void findErasedBlock(char[][] originalBoard, char[][] erasedBoard) {
        int m = originalBoard.length;
        int n = originalBoard[0].length;
        for (int row = 0; row < m - 1; row++) {
            for (int col = 0; col < n - 1; col++) {
                if (originalBoard[row][col] == originalBoard[row][col + 1]) {
                    if (originalBoard[row + 1][col] == originalBoard[row + 1][col + 1]) {
                        if (originalBoard[row][col] == originalBoard[row + 1][col]) {
                            erasedBoard[row][col] = ' ';
                            erasedBoard[row][col + 1] = ' ';
                            erasedBoard[row + 1][col] = ' ';
                            erasedBoard[row + 1][col + 1] = ' ';
                        }
                    }
                }
            }
        }
    }

    // row: 0 -> m-1, col: 0 -> n-1,  까지 돌면서 4개 붙어있으면 erasedBlock에 " " 로 변경
    // eraseBlock에서 " " 를 지우기
    // 남아있는 블럭 개수 구하기 (이전 개수보다 작으면 update, 같으면 종료)
    // erasedBlock에 뒤에 " " 로 채워서 original, erasedBlock 다시 세팅

}