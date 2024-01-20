import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        // N을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int answer = 0;

        // 0, 양수, 음수, 1을 각각 저장한다.
        int zero = 1;
        List<Integer> positiveNumbers = new ArrayList<>();
        List<Integer> negativeNumbers = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int number = Integer.parseInt(br.readLine());
            if (number > 1) {
                positiveNumbers.add(number);
            } else if (number < 0) {
                negativeNumbers.add(number);
            } else if (number == 1) {
                answer += number;
            } else {
                zero = number;
            }
        }

        // 양수는 양수끼리 내림차순 정렬한다.
        Collections.sort(positiveNumbers, Collections.reverseOrder());

        // 앞에서 부터 2개씩 곱해서 계산한다.
        Deque<Integer> sortedPositiveNumbers = new ArrayDeque<>(positiveNumbers);
        while (sortedPositiveNumbers.size() > 1) {
            answer += sortedPositiveNumbers.removeFirst() * sortedPositiveNumbers.removeFirst();
        }
        // 남은 양수는 더해준다.
        if (!sortedPositiveNumbers.isEmpty()) {
            answer += sortedPositiveNumbers.removeFirst();
        }

        // 음수는 음수끼리 오름차순 정렬한다.
        Collections.sort(negativeNumbers);

        // 앞에서 부터 2개씩 곱해서 계산한다.
        Deque<Integer> sortedNegativeNumbers = new ArrayDeque<>(negativeNumbers);
        while (sortedNegativeNumbers.size() > 1) {
            answer += sortedNegativeNumbers.removeFirst() * sortedNegativeNumbers.removeFirst();
        }

        // 홀수개가 남으면 마지막 한개에 0 여부를 곱해준다.
        if (!sortedNegativeNumbers.isEmpty()) {
            answer += sortedNegativeNumbers.removeFirst() * zero;
        }

        System.out.println(answer);
    }
}
