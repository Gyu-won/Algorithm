import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static Answer[] answers;

    public static void main(String[] args) throws IOException {
        // numberOfQuestion 을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numberOfQuestion = Integer.parseInt(br.readLine());

        // answer을 입력받고 객체로 저장한다
        StringTokenizer st;
        answers = new Answer[numberOfQuestion];
        for (int i = 0; i < numberOfQuestion; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            answers[i] = new Answer(st.nextToken(), st.nextToken(), st.nextToken());
        }

        // 전체 숫자를 구해서 조건을 만족하는 개수를 구한다.
        int count = 0;
        for (int i = 1; i < 8; i++) {
            for (int j = i + 1; j < 9; j++) {
                for (int k = j + 1; k < 10; k++) {
                    count += checkAnswers(i, j, k);
                    count += checkAnswers(i, k, j);
                    count += checkAnswers(j, i, k);
                    count += checkAnswers(j, k, i);
                    count += checkAnswers(k, i, j);
                    count += checkAnswers(k, j, i);
                }
            }
        }
        System.out.println(count);
    }

    private static int checkAnswers(int i, int j, int k) {
        for (Answer answer : answers) {
            if (!answer.isValid(new int[]{i, j, k})) {
                return 0;
            }
        }
        return 1;
    }

    private static class Answer {
        private final int[] numbers = new int[3];
        private final int strike;
        private final int ball;

        private Answer(String number, String strike, String ball) {
            this.strike = Integer.parseInt(strike);
            this.ball = Integer.parseInt(ball);
            for (int i = 0; i < 3; i++) {
                numbers[i] = number.charAt(i) - '0';
            }
        }

        public boolean isValid(int[] guess) {
            int strikeCount = 0;
            int ballCount = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (guess[i] == numbers[j]) {
                        if (i == j) {
                            strikeCount++;
                        } else {
                            ballCount++;
                        }
                    }
                }
            }
            return strike == strikeCount && ball == ballCount;
        }
    }

}

// 알고리즘: 방법이 딱히 없고, N이 100이하이고 가능한숫자가 총 1000개 이하이므로 완전탐색한다.

// 시간복잡도: 완탐

// 정수 범위