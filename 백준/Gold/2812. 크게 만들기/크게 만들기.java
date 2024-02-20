import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

        boolean[] removed = removeSmallerNumber(n, k, numbers);
       
        System.out.println(selectFrontNumbers(n, k, removed, numbers));
    }

    private static String selectFrontNumbers(int n, int k, boolean[] removed, int[] numbers) {
        int length = 0;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (length == n - k) {
                break;
            }
            if (!removed[i]) {
                result.append(numbers[i]);
                length++;
            }
        }
        return result.toString();
    }

    private static boolean[] removeSmallerNumber(int n, int k, int[] numbers) {
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

        return removed;
    }
}

// 요약
// 현재 숫자보다 뒤에 숫자가 더 크면 현재 숫자를 지우면 됨
// 다 지우고도 남았으면 그냥 뒤에 숫자를 지우면 됨 (내림차순 정렬된거임)

// 1초: O(n ^ 2)

// 11:04