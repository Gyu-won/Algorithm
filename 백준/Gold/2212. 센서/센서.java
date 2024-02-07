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
        // n을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // k를 입력받는다.
        int k = Integer.parseInt(br.readLine());

        // int[n] sensors를 입력받는다. (-1000000 - 1000000)
        int[] sensors = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            sensors[i] = Integer.parseInt(st.nextToken());
        }

        // n <= k 이면 0을 리턴한다.
        if (n <= k) {
            return 0;
        }

        // sensors를 오름차순 정렬한다.
        Arrays.sort(sensors);

        // diff[n-1]을 구하면서 diffSum도 구한다.
        int diffSum = 0;
        int[] diff = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            diff[i] = sensors[i + 1] - sensors[i];
            diffSum += diff[i];
        }

        // diff를 오름차순으로 정렬한다.
        Arrays.sort(diff);

        // diffSum에서 diff 0 - k-1 까지반복하며 뒤에서부터 뺀다.
        for (int i = 0; i < k - 1; i++) {
            diffSum -= diff[n - 2 - i];
        }

        // diffSum으 리턴한다.
        return diffSum;
    }
}

// 설계 시간: 14:45 -
// 풀이 시간:

//0. 문제요약
// n개의 센서 설치, k 개의 집중국 설치
// 집중국으 수신가능 영역에 있는 센서만 수신 가능/ 센서는 최소 하나의 집중국과는 통신
// 집중국 길이는 (0이상)
// 센서의 좌표는 중복될수도

//2. 시간복잡도: O(nlogn)

//3. 이 로직으로 예제 문제 손으로 풀기

//4. 경계값 체크 후 코드 구현

//틀렸을 때
//1. 이분탐색으로 틀린부분 찾고, 로직 생각 하기
//2. 프린트해보기, 간단한 예외케이스를 만들기

