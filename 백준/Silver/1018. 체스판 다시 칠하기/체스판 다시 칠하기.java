import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int BOARD_SIZE = 64;
    private static final int BOARD_LINE_LENGTH = 8;
    private static final char BLACK = 'B';
    private static final char WHITE = 'W';

    public static void main(String[] args) throws IOException {
        // N, M 입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 체스판 입력받기
        char[][] board = new char[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        // 가능한 체스판 돌면서 색칠 개수 구하기
        int minNumberOfChange = Integer.MAX_VALUE;
        for (int rowStart = 0; rowStart <= N - BOARD_LINE_LENGTH; rowStart++) {
            for (int colStart = 0; colStart <= M - BOARD_LINE_LENGTH; colStart++) {
                int changeCount = 0;
                for (int i = rowStart; i < rowStart + BOARD_LINE_LENGTH; i++) {
                    for (int j = colStart; j < colStart + BOARD_LINE_LENGTH; j++) {
                        // i, j 합이 짝수일 때 B, 홀수일 때 W로 해서 계산하고 64에서 뺸값 중 최소값을 저장
                        if ((i + j) % 2 == 0 && board[i][j] == BLACK) {
                            changeCount++;
                        }
                        if ((i + j) % 2 == 1 && board[i][j] == WHITE) {
                            changeCount++;
                        }
                    }
                }
                // 전체 색칠 경우 중 최소값을 저장
                changeCount = Math.min(changeCount, BOARD_SIZE - changeCount);
                minNumberOfChange = Math.min(minNumberOfChange, changeCount);
            }
        }
        System.out.println(minNumberOfChange);
    }
}