import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class Main {

    private static final int[] xDirection = new int[]{0, -1, 0, 1};
    private static final int[] yDirection = new int[]{-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public String solution() throws IOException {
        // n을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // int[n][n] map을 입력받는다.
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            String oneLine = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = oneLine.charAt(j) - '0';
            }
        }

        List<Integer> towns = new ArrayList<>();

        // 하나씩 돌면서 방문 안한 단지면 bfs를 수행한다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1) {
                    int count = 0;
                    Deque<int[]> houses = new ArrayDeque<>();
                    houses.add(new int[]{i, j});
                    map[i][j] = 0;
                    while (!houses.isEmpty()) {
                        // 방문 안한 단지면 방문하여 0으로 바꾼다.
                        count++;
                        int[] house = houses.poll();

                        for (int k = 0; k < 4; k++) {
                            // 좌우, 양옆에서 방문 안한 단지를 Dequeue<int[]> houses에 추가한다.
                            int x = house[0] + xDirection[k];
                            int y = house[1] + yDirection[k];

                            if (x >= 0 && x < n && y >= 0 && y < n && map[x][y] == 1) {
                                map[x][y] = 0;
                                houses.add(new int[]{x, y});
                            }
                        }
                    }
                    // bfs가 끝나면 count의 개수를 List<Integer> town 에 추가한다.
                    towns.add(count);
                }
            }
        }

        // town의 size와 town을 오름차순 정렬하여 출력한다.
        StringBuilder result = new StringBuilder();
        result.append(towns.size());
        result.append("\n");

        Collections.sort(towns);
        for (int town : towns) {
            result.append(town);
            result.append("\n");
        }

        return result.toString().trim();
    }
}

// 알고리즘: bfs를 사용하여 단지를 확인한다.

// 시간복잡도: O(n*n)

// 정수 범위:
