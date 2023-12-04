
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static Integer N;
    private static int targetNumber;
    private static int maxSum = 0;
    private static final List<Integer> cards = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        // N과 M을 입력받는다.
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        targetNumber = Integer.parseInt(st.nextToken());

        // 카드를 하나씩 저장한다.
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            cards.add(Integer.parseInt(st.nextToken()));
        }

        calculateMaxSum(0, 0, 0);

        // 결과 출력
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(maxSum));
        bw.flush();
        bf.close();
        bw.close();
    }

    static void calculateMaxSum(int sum, int cardCount, Integer index) {
        // 합이 목표 숫자를 넘었으면 종료한다.
        if (sum > targetNumber) {
            return;
        }

        // 카드를 3장 뽑았으면 종료한다.
        if (cardCount == 3) {
            if (maxSum < sum) {
                maxSum = sum;
            }
            return;
        }

        //index가 범위를 벗어났으면 종료한다.
        if (index.equals(N)) {
            return;
        }

        calculateMaxSum(sum + cards.get(index), cardCount + 1, index + 1);
        calculateMaxSum(sum, cardCount, index + 1);
    }
}