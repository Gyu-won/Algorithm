import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException {
        // N, M  입력받기
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());
        int i, j;

        // 바구니 초기화
        List<Integer> baskets = IntStream.rangeClosed(1, N)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));

        // M 횟수만큼 바꾸기
        for (int trial = 0; trial < M; trial++) {
            st = new StringTokenizer(bf.readLine());
            i = Integer.parseInt(st.nextToken()) - 1;
            j = Integer.parseInt(st.nextToken()) - 1;
            Collections.swap(baskets, i, j);
        }

        //결과출력
        for (int ball : baskets) {
            bw.write(Integer.toString(ball) + " ");
        }

        bw.flush();
        bf.close();
        bw.close();
    }
}