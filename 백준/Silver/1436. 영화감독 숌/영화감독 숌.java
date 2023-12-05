import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        // 풀이: 입력이 10000 이하의 자연수로 작고 시간도 2초이다. N이 10000 이라 하더라도 66600000 이내에 10000 번째 작은 값이 존재한다.
        // 6660000는 1억보다 작기 때문에 처음부터 비교해 가면서 찾아도 충분히 가능하다.
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        // N을 입력받는다.
        int N = Integer.parseInt(bf.readLine());

        // 666부터 1씩 더해가며 조건을 만족하는지 찾는다.
        int result = 666;
        int count = 0;
        while (true) {
            if (isConsecutiveThreeSix(String.valueOf(result).toCharArray())) {
                count++;
                if (count == N) {
                    break;
                }
            }
            result++;
        }

        // 결과 출력
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(result));
        bw.flush();
        bf.close();
        bw.close();
    }

    private static boolean isConsecutiveThreeSix(char[] numberDigits) {
        for (int index = 0; index < numberDigits.length - 2; index++) {
            if (numberDigits[index] == '6' && numberDigits[index + 1] == '6' && numberDigits[index + 2] == '6') {
                return true;
            }
        }
        return false;
    }
}