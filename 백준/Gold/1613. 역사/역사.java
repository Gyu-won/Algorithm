import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // n 과 k를 입력 (1-400, 1-50000)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // knows[n][n] 를 입력
        boolean[][] know = new boolean[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s1 = Integer.parseInt(st.nextToken());
            int s2 = Integer.parseInt(st.nextToken());
            know[s1][s2] = true;
        }

        // floyd
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (know[i][k] && know[k][j]) {
                        know[i][j] = true;
                    }
                }
            }
        }

        // s 입력 (1-50000)
        int s = Integer.parseInt(br.readLine());

        // unknown[s] 입력
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            if (know[start][end]) {
                result.append(-1);
            } else if (know[end][start]) {
                result.append(1);
            } else {
                result.append(0);
            }
            result.append("\n");
        }

        System.out.println(result.toString().trim());
    }

}

// 단방향 길로 해놓고, 앞에서 뒤로 길이 있으면 -1, 뒤에서 앞 길이 있으면 1 둘다 없으면 0

//12분