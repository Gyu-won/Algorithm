import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        // n, c 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        // int[] houses 입력
        int[] houses = new int[n];
        for (int i = 0; i < n; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }

        // houses 정렬
        Arrays.sort(houses);

        // 이진탐색으로 거리를 뽑기
        int start = 0;
        int end = 1000000000;
        while (start <= end) {
            int mid = (start + end) / 2;

            if (canInstall(mid, n, c, houses)) {
                // 해당 거리이상 떨어져 있을 때 c개 이상 설치 가능하면 start 옮기기
                start = mid + 1;
            } else {
                // 설치 불가능하면 end 옮기기
                end = mid - 1;
            }
        }

        System.out.println(end);
    }

    private static boolean canInstall(int distance, int n, int c, int[] houses) {
        int count = 1;

        int current = houses[0];
        for (int i = 1; i < n; i++) {
            if (houses[i] - current >= distance) {
                count++;
                current = houses[i];
            }
            if (count >= c) {
                return true;
            }
        }
        return false;
    }
}

// n 과 c가 입력되면 공유기 설치해서 최대한 멀리 있게끔

// O(nlogn) 가능

// n 이 20만이기 때문에 이진탐색 고려
// logn으로 숫자를 탐색하고, O(n)으로 c개 설치 가능한지 확인


