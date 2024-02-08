import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int[] numbers;

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public long solution() throws IOException {
        // n 이 입력된다 (1- 10000)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        // numbers 정렬
        Arrays.sort(numbers);
        return twoPointer(n);
    }

    private long twoPointer(int n) {
        long count = 0;
        for (int i = 0; i < n - 1; i++) {
            if (numbers[i] > 0) {
                break;
            }
            int start = i + 1, end = n - 1;

            while (start < end) {
                int sum = numbers[i] + numbers[start] + numbers[end];
                if (sum == 0) {
                    if (numbers[start] == numbers[end]) {
                        count += comb(end - start + 1);
                        break;
                    }
                    int sNum = 1;
                    while (start + sNum < end && numbers[start + sNum] == numbers[start]) {
                        sNum++;
                    }
                    start += sNum;

                    int eNum = 1;
                    while (end - eNum >= start && numbers[end - eNum] == numbers[end]) {
                        eNum++;
                    }
                    end -= eNum;

                    count += (long) sNum * eNum;
                } else if (sum < 0) {
                    start++;
                } else {
                    end--;
                }
            }
        }
        return count;
    }

    private int comb(int n) {
        return n * (n - 1) / 2;
    }
}

// 설계 시간: 16:55 - 17:25
// 풀이 시간: 17:25

//0. 문제요약
// 3명 팀만 참가 가능
// 세 팀원의 코딩 실력 합이 0인 팀의 개수

//2. 시간복잡도: O(100 * 100 * 100)

//3. 이 로직으로 예제 문제 손으로 풀기

//4. 경계값 체크 후 코드 구현
