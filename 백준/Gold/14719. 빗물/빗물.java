import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println(new Solution().solution());
    }

    static class Solution {
        public int solution() throws IOException {
            // h와 w를 입력받는다.(1-500)
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            // boolean[w][h] blocks 를 입력받는다. (0-h)
            boolean[][] blocks = new boolean[h][w];
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < w; j++) {
                int k = Integer.parseInt(st.nextToken());
                for (int i = 0; i < k; i++) {
                    blocks[h - 1 - i][j] = true;
                }
            }

            // 가로 줄 씩 count 한다.
            int result = 0;
            for (int i = 0; i < h; i++) {
                boolean flag = false;
                int count = 0;
                for (int j = 0; j < w; j++) {
                    if (!blocks[i][j] && flag) {
                        count++;
                    } else if (blocks[i][j]) {
                        flag = true;
                        result += count;
                        count = 0;
                    }
                }
            }

            // 가로줄이 처음 1인 부분부터 그다음 1이 나올 때까지 count
            // 1 이 나오면 더하고 아니면 끝
            return result;
        }

    }
}

// 설계 시간: 11:17 - 11:29
// 풀이 시간:

//0. 문제요약

//2. 시간복잡도: O(h*w)

//3. 이 로직으로 예제 문제 손으로 풀기

//4. 경계값 체크 후 코드 구현
