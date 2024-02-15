import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int[] parent;

    public static void main(String[] args) throws IOException {
        System.out.println(new Solution().solution());
    }

    static class Solution {
        public int solution() throws IOException {
            // n과 m을 입력받는다. (2-100,000) (1-1,000,000)
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            // List<int[3]> roads 를 입력받는다.
            List<int[]> roads = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                roads.add(new int[]{a, b, c});
            }

            // roads를 [2]를 기준으로 오름차순 정렬한다.
            Collections.sort(roads, new RoadComparator());

            parent = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }

            //크루스칼 알고리즘
            // count가 n-1일 때까지
            int count = 0, result = 0, idx = 0;
            while (count < n - 2) {
                // roads[idx]에서 들고와서 find
                int[] r = roads.get(idx++);
                if (!find(r[0], r[1])) {
                    // find가 false면 union, count 증가, result 더하기
                    count++;
                    result += r[2];
                    union(r[0], r[1]);
                }
            }

            return result;
        }

        private void union(int h1, int h2) {
            int p1 = getParent(h1);
            int p2 = getParent(h2);

            if (p1 < p2) {
                parent[p2] = p1;
            } else {
                parent[p1] = p2;
            }
        }

        private boolean find(int h1, int h2) {
            int p1 = getParent(h1);
            int p2 = getParent(h2);
            return p1 == p2;
        }

        private int getParent(int h) {
            if (parent[h] == h) {
                return h;
            }
            return getParent(parent[h]);
        }
    }

    static class RoadComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] r1, int[] r2) {
            return r1[2] - r2[2];
        }
    }
}

// 요약
// n 개의 집과 m개의 길, 양방향
// 모든 경로는 존재, 유지비가 있음
// 마을 안의 집들은 서로 연결되어야 함, 마을에는 집 하나 이상
// 비용이 최소면서 마을을 2개로 나누는 법

// 17:07

// 2초: O(mlogm, nlogm)