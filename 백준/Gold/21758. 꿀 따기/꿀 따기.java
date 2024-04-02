import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        // n 입력 (3-100,000)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // honey 입력
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] honey = new int[n];
        for (int i = 0; i < n; i++) {
            honey[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(calculateMaxHoney(n, honey));
    }

    private static int calculateMaxHoney(int n, int[] honey) {
        int[] cumulativeHoney = calculateCumulativeHoney(n, honey);
        int left = 0, right = n - 1, maxHoney = 0;

        for (int mid = left + 1; mid < right; mid++) {
            maxHoney = Math.max(maxHoney, calculateHoney(left, mid, right, honey, cumulativeHoney));
        }
        return maxHoney;
    }

    private static int calculateHoney(int left, int mid, int right, int[] honey, int[] cumulativeHoney) {
        int leftPot = cumulativeHoney[mid] + cumulativeHoney[right] - honey[right] - 2 * honey[mid];
        int midPot = cumulativeHoney[right] - honey[left] - honey[right] + honey[mid];
        int rightPot = 2 * cumulativeHoney[right] - cumulativeHoney[mid] - honey[left] - honey[mid];

        return Math.max(leftPot, Math.max(midPot, rightPot));
    }

    private static int[] calculateCumulativeHoney(int n, int[] honey) {
        int[] cumulative = new int[n];
        cumulative[0] = honey[0];

        for (int i = 1; i < n; i++) {
            cumulative[i] = cumulative[i - 1] + honey[i];
        }
        return cumulative;
    }


}

// O(nlogn)
// 양 끝은 무조건 확정, 중간에 한개의 위치를 찾으면 됨 O(n)
// 이 중 꿀통이 가장 왼쪽, 중간, 오른쪽 가능 O(3)
// 계산은 누적합으로
// 가장 왼쪽일때는 : cumulative[mid] + cumulative[right] - honey[right] - 2 * honey[mid]
// 중간일때는: cumulative[mid] + cumulative[right] - cumulative[mid] - honey[left] - honey[right] + hone[mid]
// 가장 오른쪽일떄는: 2 * cumulative[right] - cumulative[mid] - honey[left] - honey[mid]