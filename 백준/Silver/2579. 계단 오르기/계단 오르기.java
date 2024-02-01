import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public int solution() throws IOException {
        // 계단의 개수를 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numberOfStair = Integer.parseInt(br.readLine());

        // int[] stairs을 선언하고 stair[0], stair[1]을 초기화한다.
        int[] stairs = new int[numberOfStair];
        int firstStair = Integer.parseInt(br.readLine());

        if (numberOfStair == 1) {
            return firstStair;
        }

        int secondStair = Integer.parseInt(br.readLine());
        stairs[0] = firstStair;
        stairs[1] = firstStair + secondStair;

        // int[] stairsExceptBefore을 선언하고 stairExceptBefore[1] 을 초기화한다.
        int[] stairsExceptBefore = new int[numberOfStair];
        stairsExceptBefore[1] = secondStair;

        // dp로 stair[n-1]을 구한다.
        for (int i = 2; i < numberOfStair; i++) {
            int stairScore = Integer.parseInt(br.readLine());
            stairsExceptBefore[i] = stairs[i - 2] + stairScore;
            stairs[i] = Math.max(stairsExceptBefore[i], stairsExceptBefore[i - 1] + stairScore);
        }

        // stair[n-1]을 리턴한다.
        return stairs[numberOfStair - 1];
    }
}

// 알고리즘: 완전 탐색으로 모든 밟는 경우를 다 할 경우 계단이 300개기 때문에 불가능 하다. dp 사용
// n 계단의 점수 값은 이전계단을 밟은 경우와 안밟은 경우 중 최대값이다.
// 이전 계단을 밟은 경우는 이전 계단에서 이전계단을 안밟은 경우의 + 자기자신이다.
// 이전 계단을 안밟은 경우는 2칸 이전 계단의 최대값이다.

// 시간복잡도: O(n)

// 정수 범위:
