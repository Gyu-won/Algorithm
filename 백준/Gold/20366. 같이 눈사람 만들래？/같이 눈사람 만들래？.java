import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public int solution() throws IOException {
        // n을 입력받는다 (4 - 600)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // int[n] snow를 입력받는다.
        int[] snow = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            snow[i] = Integer.parseInt(st.nextToken());
        }

        // snow를 정렬한다.
        Arrays.sort(snow);

        // 엘사를 고정한다. (On^2)
        int minDiff = Integer.MAX_VALUE;
        for (int es = 0; es < n - 3; es++) {
            for (int ee = es + 3; ee < n; ee++) {
                int elsa = snow[es] + snow[ee];
                // 안나를 투포인터로 구한다.
                int as = es + 1;
                int ae = ee - 1;
                while (as < ae) {
                    int sum = snow[as] + snow[ae];
                    if (sum < elsa) {
                        as++;
                        minDiff = Math.min(elsa - sum, minDiff);
                    } else if (sum > elsa) {
                        ae--;
                        minDiff = Math.min(sum - elsa, minDiff);
                    } else {
                        return 0;
                    }
                }
            }
        }

        return minDiff;
    }
}

// 설계 시간: 20:57 - 21:20
// 풀이 시간:

//0. 문제요약
// 눈사람은 두개의 눈덩이로 이루어지고 키는 두 눈덩이의 지름 합
// n개 중 서로 다른 4개 골라서 2개 만들려고 한다.
// 눈사람 키 차이가 최소값 ->

//2. 시간복잡도:

//3. 이 로직으로 예제 문제 손으로 풀기

//4. 경계값 체크 후 코드 구현
