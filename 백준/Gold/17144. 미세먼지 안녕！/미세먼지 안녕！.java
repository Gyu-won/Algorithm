import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static final int[] xDirection = new int[]{0, -1, 0, 1};
    private static final int[] yDirection = new int[]{-1, 0, 1, 0};

    private static int r;
    private static int c;
    private static int[][] map;
    private static final AirConditioner airConditioner = new AirConditioner();

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public int solution() throws IOException {
        // r, c, t를 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        // int[r][c] map을 입력받는다
        map = new int[r][c];
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < c; j++) {
                int dust = Integer.parseInt(st.nextToken());
                // 공기청정기의 따로 저장한다. AirConditioner
                if (dust == -1) {
                    airConditioner.setLocation(i);
                    dust = 0;
                }
                map[i][j] = dust;
            }
        }

        // 아래 과정을 t번 반복한다.
        int[][] diffusion;
        for (int n = 0; n < t; n++) {
            // int[r][c] diffusion 배열에 확산되는 양을 계산한다.
            diffusion = calculateDiffusion();

            // diffusiont과 map을 더해서 update 한다.
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    map[i][j] += diffusion[i][j];
                }
            }

            // AirConditioner를 작동시킨다.
            airConditioner.operate();
        }

        // t번 후 map에 있는 미세먼지 합계를 구한다.
        int totalDust = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                totalDust += map[i][j];
            }
        }

        return totalDust;
    }

    private int[][] calculateDiffusion() {
        int[][] diffusion = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] > 4) {
                    int diffusionAmount = map[i][j] / 5;
                    for (int k = 0; k < xDirection.length; k++) {
                        int x = i + xDirection[k];
                        int y = j + yDirection[k];
                        if (x >= 0 && x < r && y >= 0 && y < c && !airConditioner.isExist(x, y)) {
                            diffusion[x][y] += diffusionAmount;
                            map[i][j] -= diffusionAmount;
                        }
                    }
                }
            }
        }
        return diffusion;
    }

    private static class AirConditioner {
        private List<Integer> x = new ArrayList<>();

        public void setLocation(int x) {
            this.x.add(x);
        }

        public void operate() {
            int col = 0;

            int row = x.get(0);
            for (; row > 0; row--) {
                map[row][col] = map[row - 1][col];
            }
            map[x.get(0)][0] = 0;
            for (; col < c - 1; col++) {
                map[row][col] = map[row][col + 1];
            }
            for (; row < x.get(0); row++) {
                map[row][col] = map[row + 1][col];
            }
            for (; col > 0; col--) {
                map[row][col] = map[row][col - 1];
            }

            for (row = x.get(1); row < r - 1; row++) {
                map[row][col] = map[row + 1][col];
            }
            map[x.get(1)][0] = 0;
            for (; col < c - 1; col++) {
                map[row][col] = map[row][col + 1];
            }
            for (; row > x.get(1); row--) {
                map[row][col] = map[row - 1][col];
            }
            for (; col > 0; col--) {
                map[row][col] = map[row][col - 1];
            }
        }

        public boolean isExist(int x, int y) {
            return y == 0 && this.x.contains(x);
        }
    }
}

//09:18 -
// 알고리즘: 순서대로 적용하면서 전체 미세먼지양을 구하면 된다. (시뮬레이션)

// 시간복잡도: O(r*c)

// 정수 범위:
