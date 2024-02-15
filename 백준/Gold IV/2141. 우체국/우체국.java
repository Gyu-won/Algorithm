import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println(new Solution().solution());
    }

    static class Solution {
        public int solution() throws IOException {
            // n을 입력받는다.
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(br.readLine());

            // List<int[2]> town를 입력받는다.
            List<int[]> towns = new ArrayList<>();
            StringTokenizer st;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int[] town = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
                towns.add(town);
            }

            // towns 정렬
            Collections.sort(towns, new TownComparator());

            long leftP = towns.get(0)[1];
            long rightP = 0;
            for (int i = 0; i < n; i++) {
                rightP += towns.get(i)[1];
            }

            // leftp는 자기자신, rightp는 오른쪽 애들로 세팅 long
            rightP -= leftP;

            int idx = 1;
            for (; idx < n; idx++) {
                // 왼 < 오 일떄까지 반복
                if (leftP >= rightP) {
                    break;
                }

                // 점에서는 자기 값을 왼에 더하고, 오에 뺴기
                int p = towns.get(idx)[1];
                leftP += p;
                rightP -= p;
            }

            //최소값 출력
            return towns.get(idx - 1)[0];
        }

        private class TownComparator implements Comparator<int[]> {
            @Override
            public int compare(int[] t1, int[] t2) {
                return t1[0] - t2[0];
            }
        }
    }
}

// 요약
// n개의 마을 ( 1-100,000)
//x[i] 마을의 위치 (-1,000,000,000 - 1,000,000,000)
// a[i] 사람 사눈 수 (1-1,000,000,000)
// 각 사람들까지 거리의 합이 최소가 되는 위치에 우체국
// 여러개인 경우에는 더 작은 위치

// 시작 점 구하고
// 왼 사람 더하고, 오 사람 뻄
// 점이면 x[i]를 오에서 빼고 왼에 더함

// 11:24 -

// 2초: O(n)