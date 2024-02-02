import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public int solution() throws IOException {
        // A 와 B를 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        // count를 1로 선언한다.
        int count = 1;

        // A보다 B가 작거나 같을때까지 반복한다.
        while (A < B) {
            int lastNumber = B % 10;
            if (lastNumber == 1) {
                // 끝에 숫자가 1이면 10으로 나눈다.
                B /= 10;
            } else if (lastNumber % 2 == 0) {
                // 짝수이면 2로 나눈다.
                B /= 2;
            } else {
                // 1이 아닌 홀수이면 -1을 출력한다.
                return -1;
            }
            count++;
        }

        // A와 B가 같으면 count를, 다르면 -1을 리턴한다.
        return A == B ? count : -1;
    }
}

// 알고리즘: 끝에 숫자가 1이면 무조건 1을 더한거고, 그게 아니면 2을 곱한거라서 이를 숫자까지 반복한다.

// 시간복잡도:

// 정수 범위:
