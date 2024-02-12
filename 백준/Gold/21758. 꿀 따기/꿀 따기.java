import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println(new Solution().solution());
    }

    static class Solution {
        public int solution() throws IOException {
            // n을 입력받는다 (3-100000)
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(br.readLine());

            // int[n] p 를 입력받는다.
            int[] p = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < n; i++) {
                p[i] = Integer.parseInt(st.nextToken());
            }

            int[] partSum = new int[n];
            partSum[0] = p[0];
            for (int i = 1; i < n; i++) {
                partSum[i] = partSum[i - 1] + p[i];
            }

            int maxSum = 0;
            for (int i = 0; i < n; i++) {
                int sum = 0;
                int lSum = partSum[i] - p[0];
                int rSum = partSum[n - 2] - partSum[i] + p[i];
                if (lSum < rSum) {
                    sum += rSum;
                    int rightSum = 0;
                    for (int j = i + 1; j < n - 1; j++) {
                        rightSum = Math.max(rightSum, partSum[j] - partSum[i] + p[i] - 2 * p[j]);
                    }
                    sum += Math.max(rightSum, lSum);
                } else if (lSum > rSum) {
                    sum += lSum;
                    int leftSum = 0;
                    for (int j = 1; j < i; j++) {
                        leftSum = Math.max(leftSum, partSum[i] - partSum[j] - p[j]);
                    }
                    sum += Math.max(leftSum, rSum);
                } else {
                    sum += lSum * 2;
                }
                maxSum = Math.max(maxSum, sum);
            }

            return maxSum;
        }
    }
}

// 설계 시간: 00:40 - 00:58
// 풀이 시간:

//0. 문제요약
// 좌우 n개의 장소 가 있다.
// 2장소에는 벌을 한 장소에는 벌통을 둔다
// 두 마리 벌이 날아가면서 꿀을 따는데 시작 장소 뺴고는 모두 표시된 숫자만큼 꿀을 딴다
// 최대 꿀 따는 꿀 양 구해라

//2. 시간복잡도:

//3. 이 로직으로 예제 문제 손으로 풀기

//4. 경계값 체크 후 코드 구현
