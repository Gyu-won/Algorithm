import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        // n을 입력받는다 (1-100)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // int[n+1] numbers를 입력받는다.
        int[] numbers = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        List<Integer> answers = new ArrayList<>();
        // 1 - n까지 실행
        for (int firstNumber = 1; firstNumber <= n; firstNumber++) {
            boolean[] visited = new boolean[n + 1];
            if (dfs(firstNumber, numbers[firstNumber], numbers, visited)) {
                answers.add(firstNumber);
            }
        }

        System.out.println(createResult(answers));
    }

    private static String createResult(List<Integer> answers) {
        StringBuilder result = new StringBuilder();
        result.append(answers.size());
        result.append("\n");

        for (int answer : answers) {
            result.append(answer);
            result.append("\n");
        }
        return result.toString().trim();
    }

    private static boolean dfs(int startNumber, int currentNumber, int[] numbers, boolean[] visited) {
        if (startNumber == currentNumber) {
            return true;
        }
        if (visited[currentNumber]) {
            return false;
        }
        visited[currentNumber] = true;
        return dfs(startNumber, numbers[currentNumber], numbers, visited);
    }
}

// 요약
// 2xn 표, 첫째줄은 1-n, 둘쨰줄은 1-n사이의 정수
// 첫째줄 뽑은 집합이랑 둘째줄 뽑은 집합 같도록 한다.
// 최디한 많이 뽑는 방법 구하기, 집합 크기 최대

// 1초: O(n * n)

// 12:02