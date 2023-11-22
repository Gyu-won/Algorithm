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
    private static final int NUMBER_OF_TOTAL_STUDENT = 30;
    private static final int NUMBER_OF_SUBMIT_STUDENT = 28;

    public static void main(String[] args) throws IOException {
        // N, M 입력받기
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());

        // N개의 바구니 생성
        List<Integer> baskets = IntStream.rangeClosed(1, N)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));

        // M번 만큼 바구니 순서 변경
        int i, j;
        for (int iter = 0; iter < M; iter++) {
            st = new StringTokenizer(bf.readLine());
            i = Integer.parseInt(st.nextToken()) - 1;
            j = Integer.parseInt(st.nextToken());
            Collections.reverse(baskets.subList(i, j));
        }

        // 최종 결과 출력
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int basket : baskets) {
            bw.write(Integer.toString(basket) + " ");
        }

        bw.flush();
        bf.close();
        bw.close();
    }
}