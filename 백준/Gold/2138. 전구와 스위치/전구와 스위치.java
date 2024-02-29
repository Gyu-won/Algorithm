import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        // n 입력 (2-100,000)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] bulbs = new int[n + 1];
        int[] target = new int[n + 1];

        char[] temp = br.readLine().toCharArray();
        for (int i = 0; i < n; i++) {
            bulbs[i] = temp[i] - '0';
        }

        temp = br.readLine().toCharArray();
        for (int i = 0; i < n; i++) {
            target[i] = temp[i] - '0';
        }

        int[] otherBulbs = new int[n + 1];
        for (int i = 0; i < n; i++) {
            otherBulbs[i] = bulbs[i];
        }
        otherBulbs[0] = 1 - otherBulbs[0];
        otherBulbs[1] = 1 - otherBulbs[1];

        int cnt1 = pushButton(n, bulbs, target, 0);
        int cnt2 = pushButton(n, otherBulbs, target, 1);

        int minValue = Math.min(cnt1, cnt2);
        if (minValue == Integer.MAX_VALUE){
            System.out.println(-1);
            return;
        }
        System.out.println(minValue);
    }

    private static int pushButton(int n, int[] bulbs, int[] target, int count) {
        for (int i = 0; i < n - 1; i++) {
            if (bulbs[i] != target[i]) {
                count++;
                bulbs[i] = 1 - bulbs[i];
                bulbs[i + 1] = 1 - bulbs[i + 1];
                bulbs[i + 2] = 1 - bulbs[i + 2];
            }
        }

        if (bulbs[n - 1] == target[n - 1]) {
            return count;
        }
        return Integer.MAX_VALUE;
    }

}

// n개의 전구와 스위치
// 중간꺼 누르면 앞뒤랑 자신 바뀜
// 1번은 1, 2번 / n번은 n-1, n번
// 만들고자 하는 상태가 주어질때 최소 스위치 누르는 횟수

// O(nlogn) 가능