import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    public void solution() throws IOException {
        // numberByA, numberByB를 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        String numberByA = st.nextToken();
        String numberByB = st.nextToken();

        // nubmerByA와 numberByB를 10진법으로 변환하여 배열로 저장한다.
        long[] parsedNumberA = parsedNumber(numberByA);
        long[] parsedNumberB = parsedNumber(numberByB);

        // 같은 수가 있는지 확인하고 값을 세팅한다.
        int count = 0;
        StringBuilder answer = new StringBuilder();
        for (int i = 2; i < 37; i++) {
            if (parsedNumberA[i] == -1) {
                continue;
            }
            for (int j = 2; j < 37; j++) {
                if (parsedNumberB[j] == -1) {
                    continue;
                }
                if (i != j && parsedNumberA[i] == parsedNumberB[j]) {
                    count++;
                    answer.append(parsedNumberA[i]);
                    answer.append(" ");
                    answer.append(i);
                    answer.append(" ");
                    answer.append(j);
                }
            }
        }

        // 결과를 출력한다.
        if (count > 1) {
            System.out.println("Multiple");
        } else if (count == 0) {
            System.out.println("Impossible");
        } else {
            System.out.println(answer);
        }
    }

    private long[] parsedNumber(String number) {
        long[] parsedNumber = new long[37];
        for (int i = 2; i < 37; i++) {
            try {
                parsedNumber[i] = Long.parseLong(number, i);
            } catch (NumberFormatException exception) {
                parsedNumber[i] = -1;
            }
        }
        return parsedNumber;
    }
}

// 알고리즘: numberByB를 10진법으로 변환하여 배열로 저장한다.
// numberByA를 변환하였을 떄 값을 비교하여 같은 값이면 count를 증가시키고, X, A, B를 세팅한다.
// count가 2보다 크면 multiple, count가 1이면 결과 출력, count가 0이면 Impossible을 출력한다.

// 시간복잡도: O(35 **2)

// 정수 범위: X만 long으로
