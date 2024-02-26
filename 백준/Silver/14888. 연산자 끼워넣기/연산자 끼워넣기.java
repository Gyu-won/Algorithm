import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int maxValue = Integer.MIN_VALUE;
    private static int minValue = Integer.MAX_VALUE;
    private static int[] numbers;

    public static void main(String[] args) throws IOException {
        // n 을 입력 (2-11)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // int numbers를 입력 (1-100)
        numbers = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        // operator를 입력받는다.
        int[] operators = new int[n - 1];
        int idx = 0;
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 4; i++) {
            int number = Integer.parseInt(st.nextToken());
            for (int j = 0; j < number; j++) {
                operators[idx++] = i;
            }
        }

        // char[] operators를 생성한다.
        dfs(n - 1, new boolean[n - 1], new ArrayList<>(), operators);

        // 계산 결과를 계산하여 최소값, 최대값을 update 한다.

        System.out.println(maxValue);
        System.out.println(minValue);
    }

    private static void dfs(int n, boolean[] visited, List<Integer> current, int[] operators) {
        if (current.size() == n) {
            int[] operates = new int[n];
            for (int i = 0; i < n; i++) {
                operates[i] = current.get(i);
            }
            calculate(operates);
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                current.add(operators[i]);
                dfs(n, visited, current, operators);
                current.remove(current.size() - 1);
                visited[i] = false;
            }

        }
    }

    private static void calculate(int[] operates) {
        int number = numbers[0];
        for (int i = 0; i < operates.length; i++) {
            if (operates[i] == 0) {
                number += numbers[i + 1];
            } else if (operates[i] == 1) {
                number -= numbers[i + 1];
            } else if (operates[i] == 2) {
                number *= numbers[i + 1];
            } else {
                if (number < 0) {
                    number = number * -1 / numbers[i + 1] * -1;
                } else {
                    number /= numbers[i + 1];
                }
            }
        }
        maxValue = Math.max(maxValue, number);
        minValue = Math.min(minValue, number);
    }

}

//

//

// 22:46
