import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final int NUMBER_SIZE = 12;

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public String solution() throws IOException {
        // t를 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        // number[] 배열을 선언하고, 1, 2, 3의 값을 할당한다.
        int[] number = new int[NUMBER_SIZE];
        number[1] = 1;
        number[2] = 2;
        number[3] = 4;

        // number[] 배열을 11까지 채운다.
        for (int i = 4; i < NUMBER_SIZE; i++) {
            number[i] = number[i - 1] + number[i - 2] + number[i - 3];
        }

        // t 만큼 n을 입력받으며 결과를 계산한다.
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            result.append(number[n]);
            result.append("\n");
        }

        return result.toString().trim();
    }
}

// 알고리즘: 1, 2, 3으로 표현하는 것이기 때문에 뭔가 이전것과 관계가 있을 것 같아 dp를 떠올렸다.
// number[i] = number[i-1] + number[i-2] + number[i-3]

// 시간복잡도:

// 정수 범위
