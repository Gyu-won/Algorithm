import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public int solution() throws IOException {
        // n 을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // n을 시작으로 숫자를 키워가며 조건을 확인한다.
        int number = n;
        while (true) {
            String stringNumber = String.valueOf(number);
            if (isPalindrome(stringNumber) && isPrime(number)) {
                return number;
            }
            number++;
        }

        // 값을 출력한다.
    }

    private boolean isPrime(int number) {
        if (number == 1) {
            return false;
        }
        if (number == 2) {
            return true;
        }
        if (number % 2 == 0) {
            return false;
        }
        for (int i = 3; i <= Math.sqrt(number); i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    private boolean isPalindrome(String stringNumber) {
        StringBuilder reverseNumber = new StringBuilder(stringNumber);
        return stringNumber.contentEquals(reverseNumber.reverse());
    }
}

// 알고리즘: 팰린드롬은 String.reverse로 판단하면 된다.
// 소수인지는 범위가 안정해져 있어서 판단이 어렵다.
// 일단 무식한 방식으로 n이 1000000일떄 조건을 만족하는 값을 구하고, 그 값까지 소수 여부 배열을 만들어서 시간을 단축한다.

// 시간복잡도: O()

// 정수 범위
