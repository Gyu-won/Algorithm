import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    private static final int[] dr = new int[]{0, -1, 0, 1, 0};
    private static final int[] dc = new int[]{0, 0, 1, 0, -1};

    private static char[][] map;
    private static Deque<Bomb> bombs = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public String solution() throws IOException {
        // r, c, n을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        // char[r][c] a를 입력받는다.
        map = new char[r][c];
        for (int i = 0; i < r; i++) {
            String row = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = row.charAt(j);
            }
        }

        // n이 1이면 현재 상태 리턴
        if (n == 1) {
            return printMap();
        }

        // 1초에 Bomb을 큐에 넣는다.
        checkBombs();

        // 2부터 n까지 반복한다.
        for (int k = 2; k <= n; k++) {
            // 짝수 시간대에 a를 전체 폭탄으로 update
            if (k % 2 == 0) {
                for (int i = 0; i < r; i++) {
                    for (int j = 0; j < c; j++) {
                        map[i][j] = 'O';
                    }
                }
            } else {
                // 홀수 시간대에 queue를 확인해서 t가 같으면 pop 하면서 터짐
                while (!bombs.isEmpty()) {
                    Bomb bomb = bombs.poll();

                    // 폭탄 터짐
                    bomb.explode();

                }

                // 남은 폭탄 넣기
                checkBombs();
            }

        }

        //a를 리턴한다.
        return printMap();
    }

    private void checkBombs() {
        // 남은 폭탄 큐에 추가
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 'O') {
                    bombs.offer(new Bomb(i, j));
                }
            }
        }
    }

    private String printMap() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                result.append(map[i][j]);
            }
            result.append("\n");
        }
        return result.toString().trim();
    }

    private class Bomb {
        private final int r;
        private final int c;

        Bomb(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public void explode() {
            for (int k = 0; k < 5; k++) {
                int row = r + dr[k];
                int col = c + dc[k];

                if (row >= 0 && row < map.length && col >= 0 && col < map[0].length) {
                    map[row][col] = '.';
                }
            }
        }
    }
}

// 설계 시간: 10:43 - 11:08
// 풀이 시간: 11:08 - 11:29

//0. 문제요약
// 칸은 비어있거나 폭탄 / 폭탄은 3초뒤 폭발, 4방향과 자기자신이 파괴됨, 연쇄반응 x
// 0, 1초 폭탄 설치 / 2초 모든칸 폭탄 설치/ 3초 처음 폭탄 터진다 / 4초 모두 폭탄 설치 / 5초 후 2번째 폭탄 터진다.
//n초 후의 격자판 상태

//2. 시간복잡도: O(n * r * c)

//3. 이 로직으로 예제 문제 손으로 풀기

//4. 경계값 체크 후 코드 구현

//틀렸을 때
//1. 이분탐색으로 틀린부분 찾고, 로직 생각 하기
//2. 프린트해보기, 간단한 예외케이스를 만들기

