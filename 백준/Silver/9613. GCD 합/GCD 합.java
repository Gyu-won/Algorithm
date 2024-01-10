import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

// 약수를 모두 구하기 (1부터 1000까지로 나눠서 약수를 구할 수 있음 Set<Integer> 반환
// set에 넣고, 최대값 구하기


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            List<Set<Integer>> divisors = new ArrayList<>();

            for (int j = 0; j < n; j++) {
                divisors.add(calculateDivisors(Integer.parseInt(st.nextToken())));
            }

            long sum = 0;
            for (int j = 0; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    Set<Integer> commonDivisor = new HashSet<>(divisors.get(j));
                    commonDivisor.retainAll(divisors.get(k));
                    sum += Collections.max(commonDivisor);
                }
            }
            result.append(sum);
            result.append("\n");
        }
        System.out.println(result);
    }

    private static Set<Integer> calculateDivisors(int number) {
        Set<Integer> divisors = new HashSet<>();
        for (int i = 1; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                divisors.add(i);
                divisors.add(number / i);
            }
        }
        return divisors;
    }
}