import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // N, M  입력받기
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());

        int i, j, k;
        Integer[] balls = new Integer[N];
        Arrays.fill(balls, 0);

        // M 횟수만큼 공을 넣기
        for (int trial = 0; trial < M; trial++) {
            st = new StringTokenizer(bf.readLine());
            i = Integer.parseInt(st.nextToken()) - 1;
            j = Integer.parseInt(st.nextToken()) - 1;
            k = Integer.parseInt(st.nextToken());

            for (int index = i; index <= j; index++) {
                balls[index] = k;
            }
        }

        for (Integer ball : balls) {
            bw.write(Integer.toString(ball) + " ");
        }

        bw.flush();
        bf.close();
        bw.close();
    }
}