import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        // n 과 k를 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        String number = br.readLine();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = number.charAt(i) - '0';
        }

        List<Integer> sortedNumbers = removeSmallerNumber(n, k, numbers);

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n - k; i++) {
            result.append(sortedNumbers.get(i));
        }
        System.out.println(result.toString());
    }

    private static List<Integer> removeSmallerNumber(int n, int k, int[] numbers) {
        boolean[] removed = new boolean[n];

        int count = 0;

        exit:
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (k == count) {
                    break exit;
                }
                if (!removed[j]) {
                    if (numbers[j] < numbers[i]) {
                        removed[j] = true;
                        count++;
                    } else {
                        break;
                    }
                }
            }
        }

        List<Integer> sortedNumbers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!removed[i]) {
                sortedNumbers.add(numbers[i]);
            }
        }
        return sortedNumbers;
    }
}

// 요약
// 현재 숫자보다 뒤에 숫자가 더 크면 현재 숫자를 지우면 됨
// 다 지우고도 남았으면 그냥 뒤에 숫자를 지우면 됨 (내림차순 정렬된거임)

// 1초: O(n ^ 2)

// 11:04