import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static int m;
    private static int[] directionX = new int[]{0, -1, 0, 1};
    private static int[] directionY = new int[]{-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public int solution() throws IOException {
        // n과 m을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 미로를 입력받는다. int[n+1][m+1] maze;
        int[][] maze = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            String blocks = br.readLine();
            for (int j = 0; j < m; j++) {
                maze[i][j + 1] = blocks.charAt(j) - '0';
            }
        }

        // 1, 1에서 출발하여, count를 1 증가하고, 갈 수 있는 곳을 queue에 넣는다.
        int count = 1;
        boolean[][] visited = new boolean[n + 1][m + 1];

        Deque<Block> route = new ArrayDeque<>();
        Block current = new Block(1, 1, count);
        route.add(current);
        visited[current.x][current.y] = true;

        while (!route.isEmpty()) {
            current = route.poll();
            count = current.count;

            if (current.isDestination()) {
                break;
            }

            for (int i = 0; i < 4; i++) {
                int x = current.x + directionX[i];
                int y = current.y + directionY[i];

                if (x > 0 && y > 0 && x <= n && y <= m && maze[x][y] == 1 && !visited[x][y]) {
                    visited[x][y] = true;
                    route.add(new Block(x, y, count + 1));
                }
            }
        }
        return current.count;
    }

    private class Block {
        private final int x;
        private final int y;
        private final int count;

        public Block(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        public boolean isDestination() {
            return x == n && y == m;
        }
    }
}

// 알고리즘: 최소 거리를 구하면 되기 때문에 bfs를 사용한다.

// 시간복잡도: O(n*n)

// 정수 범위:
