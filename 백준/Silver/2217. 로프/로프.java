import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        // n 입력 (1-100000)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // ropes 입력
        int[] ropes = new int[n];
        for (int i = 0; i < n; i++) {
            ropes[i] = Integer.parseInt(br.readLine());
        }

        // 오름차순 정렬
        Arrays.sort(ropes);

        // 자신에서 전체 개수를 빼서 곱한것이 무게
        int maxValue = 0;
        for (int i = 0; i < n; i++) {
            int weight = ropes[i] * (n - i);
            maxValue = Math.max(maxValue, weight);
        }

        // 최대값 출력
        System.out.println(maxValue);
    }

    // 로프로 들어올릴 수 있는 최대 중량, 모두 사용할 필요 x

    // O(nlogn) 가능
}

