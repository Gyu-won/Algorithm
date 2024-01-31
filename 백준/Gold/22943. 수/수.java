import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int k;
    private static boolean[] visited = new boolean[10];
    private static List<Integer> numbers;
    private static int maxNumber = 0;
    private static boolean[] isNotPrime;
    private static List<Integer> primes;

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public int solution() throws IOException {
        // k와 m을 입력받는다 (int)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        k = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // List<Integer> numbers을 구한다.
        numbers = new ArrayList<>();
        combination(0, "");

        // List<Integer> primes를 구한다.
        isNotPrime = new boolean[maxNumber];
        isNotPrime[0] = true;
        isNotPrime[1] = true;
        for (int i = 2; i <= Math.sqrt(maxNumber); i++) {
            if (!isNotPrime[i]) {
                for (int j = i * i; j < maxNumber; j += i) {
                    isNotPrime[j] = true;
                }
            }
        }

        primes = new ArrayList<>();
        for (int i = 2; i < maxNumber; i++) {
            if (!isNotPrime[i]) {
                primes.add(i);
            }
        }

        // 조건을 판단한다.
        int count = 0;
        for (int number : numbers) {
            if (isSumOfPrimes(number)) {
                while (number % m == 0) {
                    number /= m;
                }
                if (isMultipleOfPrime(number)) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isMultipleOfPrime(int number) {
        for (int primeNumber : primes) {
            if (primeNumber >= number) {
                break;
            }
            if (number % primeNumber == 0 && !isNotPrime[number / primeNumber]) {
                return true;
            }
        }
        return false;
    }

    private boolean isSumOfPrimes(int number) {
        for (int primeNumber : primes) {
            if (primeNumber >= number) {
                break;
            }
            int n = number - primeNumber;
            if (n != primeNumber && !isNotPrime[n]) {
                return true;
            }
        }
        return false;
    }

    private void combination(int count, String number) {
        if (count == k) {
            int parsedNumber = Integer.parseInt(number);
            numbers.add(parsedNumber);
            maxNumber = Math.max(maxNumber, parsedNumber);
            return;
        }
        for (int i = 0; i < 10; i++) {
            if (count == 0 && i == 0) {
                continue;
            }
            if (!visited[i]) {
                visited[i] = true;
                combination(count + 1, number + i);
                visited[i] = false;
            }
        }
    }
}

// 15:43 -

// 알고리즘: 일단 숫자를 만들고 조건을 체크해야한다.
// 조합 알고리즘을 사용하여 k에 해당하는 모든 숫자를 구한다
// 최대값 보다 작은 소수를 에라토스테네스의 채로 구한다.
// 소수의 합으로 이루어져 있는지, m으로 나눈 나머지가 소수의 곱인지 구한다.

// 시간복잡도: O()

// 정수 범위
