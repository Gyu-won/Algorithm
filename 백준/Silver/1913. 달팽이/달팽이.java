import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final int[] rowDirection = new int[]{-1, 0, 1, 0};
    private static final int[] colDirection = new int[]{0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    public void solution() throws IOException {
        // N을 입력받는다. (int)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // table을 생성한다.
        int[][] table = new int[N][N];

        // findNumber를 입력받는다. (int)
        int findNumber = Integer.parseInt(br.readLine());

        // 초기 값 세팅
        int row = N / 2, col = N / 2;
        table[row][col] = 1;
        int findX = row + 1, findY = col + 1;
        int direction = 0;

        // 1 - N**2 까지 값을 넣는다.
        for (int number = 2; number <= N * N; number++) {
            int current = direction % 4;
            if (table[row + rowDirection[current]][col + colDirection[current]] == 0) {
                direction++;
            } else {
                current = (direction - 1) % 4;
            }
            row += rowDirection[current];
            col += colDirection[current];
            table[row][col] = number;

            if (findNumber == number) {
                findX = row + 1;
                findY = col + 1;
            }
        }

        // 배열을 출력한다.
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < N; i++) {
            StringBuilder oneRow = new StringBuilder();
            for (int j = 0; j < N; j++) {
                oneRow.append(table[i][j]);
                oneRow.append(" ");
            }
            result.append(oneRow.toString());
            result.append("\n");
        }

        // 찾고자하는 숫자의 위치를 출력한다.
        result.append(findX);
        result.append(" ");
        result.append(findY);

        System.out.println(result);
    }
}

// 알고리즘: 위, 오, 아, 왼 순으로 돌면서 가되 방문한 곳이면 현재 방향으로 한번 더 간다.
// 0, 0에 도달하면 종료
// 또한 findNumber가 나왔을때의 i, j 값을 저장한다.

// 시간복잡도:O(N**2)

// 정수 범위:
