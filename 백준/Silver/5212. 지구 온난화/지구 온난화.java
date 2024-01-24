import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main extends Object {

    private static int[][] currentMap;
    private static char[][] futureMap;
    private static int[] rowMove = new int[]{-1, 0, 1, 0};
    private static int[] colMove = new int[]{0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        // row 와 col을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());

        // 현재 지도를 int[][] currentMap에 입력받는다.
        currentMap = new int[row + 2][col + 2];
        futureMap = new char[row][col];
        for (int i = 1; i <= row; i++) {
            String rowMap = br.readLine();
            for (int j = 1; j <= col; j++) {
                if (rowMap.charAt(j - 1) == 'X') {
                    currentMap[i][j] = 1;
                }
            }
            Arrays.fill(futureMap[i - 1], '.');
        }

        // currentMap을 한칸씩 돌면서 침수 여부를 확인한다.
        // minRow, maxRow, minCol, maxCol을 업데이트한다. (int)
        int minRow = row, maxRow = 0, minCol = col, maxCol = 0;
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if (currentMap[i][j] == 1 && isRemain(i, j)) {
                    minRow = Math.min(minRow, i);
                    maxRow = Math.max(maxRow, i);
                    minCol = Math.min(minCol, j);
                    maxCol = Math.max(maxCol, j);
                    futureMap[i - 1][j - 1] = 'X';
                }
            }
        }

        // row와 col의 min 값부터 지도를 출력한다.
        StringBuilder answer = new StringBuilder();
        for (int i = minRow; i <= maxRow; i++) {
            for (int j = minCol; j <= maxCol; j++) {
                answer.append(futureMap[i - 1][j - 1]);
            }
            answer.append("\n");
        }
        System.out.println(answer);
    }

    private static boolean isRemain(int row, int col) {
        int numberOfIsland = 0;
        for (int i = 0; i < 4; i++) {
            numberOfIsland += currentMap[row + rowMove[i]][col + colMove[i]];
            if (numberOfIsland > 1) {
                return true;
            }
        }
        return false;
    }
}

// 알고리즘: 지도의 크기가 주어지고, 상하좌우를 살펴보는 시뮬레이션

// 시간복잡도: O(R*C)

// 정수 범위
