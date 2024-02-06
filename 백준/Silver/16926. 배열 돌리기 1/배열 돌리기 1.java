import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public String solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[][] a = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int t = Math.min(n, m) / 2;

        for (int k = 0; k < t; k++) {
            int mrt = r % ((n + m - 4 * k) * 2 - 4);
            for (int rt = 0; rt < mrt; rt++) {
                int temp = a[k][k];
                int i = k, j = k;
                while (j < m - 1 - k) {
                    a[i][j] = a[i][j + 1];
                    j++;
                }
                while (i < n - 1 - k) {
                    a[i][j] = a[i + 1][j];
                    i++;
                }
                while (j > k) {
                    a[i][j] = a[i][j - 1];
                    j--;
                }
                while (i > k + 1) {
                    a[i][j] = a[i - 1][j];
                    i--;
                }
                a[k + 1][k] = temp;
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            StringBuilder oneRow = new StringBuilder();
            for (int j = 0; j < m; j++) {
                oneRow.append(a[i][j]);
                oneRow.append(" ");
            }
            result.append(oneRow.toString().trim());
            result.append("\n");
        }
        return result.toString().trim();
    }
}

// 설계 시간: 10:51 - 11:23
// 풀이 시간: 11:23 - 11:34

//1. 로직을 분할하고, 수도코드 작성(자료형)
// 입력받기
// n, m, r을 입력받는다.
// int[n][n] a를 입력받는다.
// n과 m 중 최소값/2 구하기 t

// 회전하기
// for k in 0 - t:
// for rt : 0 - r % (n + m - 4 * k) * 2 - 4 번 회전
// temp = a[k][k]
// i, j = k;
// while(j < m-1-k): a[i][j] = a[i][j+1]; j++
// while(i < n-1-k): a[i][j] = a[i+1][j]; i++
// while(j > k): a[i][j] = a[i][j-1]; j--
// while(i > k+1): a[i][j] = a[i-1][j]; i--
// a[k+1][k] = temp;

// 결과 출력하기

//2. 시간복잡도: O(r * n * m)

//3. 이 로직으로 예제 문제 손으로 풀기

//4. 경계값 체크 후 코드 구현

//틀렸을 때
//1. 이분탐색으로 틀린부분 찾고, 로직 생각 하기
//2. 프린트해보기, 간단한 예외케이스를 만들기

