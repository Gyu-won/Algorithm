import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    private static final int[] dr = new int[]{0, 1, 1};
    private static final int[] dc = new int[]{1, 1, 0};

    private static int[][] house;
    private static int result = 0;
    private static Deque<Pipe> pipes = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        // n 입력 (3-16)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // house 입력
        StringTokenizer st;
        house = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                house[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // queue 세팅
        pipes.add(new Pipe(0, 1, 1, 0));

        //
        while (!pipes.isEmpty()) {
            Pipe pipe = pipes.removeFirst();
            moveByShape(n, pipe);
        }
        System.out.println(result);
    }

    private static void moveByShape(int n, Pipe pipe) {
        if (pipe.shape == 0) {
            for (int i = 0; i < 2; i++) {
                move(pipe, i, n);
            }
        } else if (pipe.shape == 1) {
            for (int i = 0; i < 3; i++) {
                move(pipe, i, n);
            }
        } else {
            for (int i = 1; i < 3; i++) {
                move(pipe, i, n);
            }
        }
    }

    private static void move(Pipe pipe, int d, int n) {
        int x = pipe.r + dr[d];
        int y = pipe.c + dc[d];

        if (isValid(x, y, d, n)) {
            if (x == n - 1 && y == n - 1) {
                result += pipe.time;
            } else {
                pipes.offer(new Pipe(x, y, pipe.time, d));
            }
        }
    }

    private static boolean isValid(int x, int y, int d, int n) {
        if (d == 1) {
            return x < n && y < n && house[x][y] == 0 && house[x - 1][y] == 0 && house[x][y - 1] == 0;
        }
        return x < n && y < n && house[x][y] == 0;
    }

    private static class Pipe {
        private final int r;
        private final int c;
        private final int time;
        private final int shape;

        Pipe(int r, int c, int time, int shape) {
            this.r = r;
            this.c = c;
            this.time = time;
            this.shape = shape;
        }
    }
}

// nxn 격자판, 빈칸이거나 벽
// 파이프를 n, n으로 이동시키는 방법의 개수

// 10:51 -