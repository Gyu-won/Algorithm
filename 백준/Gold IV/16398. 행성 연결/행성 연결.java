
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
        public long solution() throws IOException {
            // n을 입력받는다 ( 1- 1000)
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(br.readLine());

            // List<int[]> flows를 입력받는다. (1-100,000,000) (i, j, p)
            List<int[]> flows = new ArrayList<>();
            StringTokenizer st;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < n; j++) {
                    int p = Integer.parseInt(st.nextToken());
                    // i == j 일 떄는 추가 안함
                    if (i < j) {
                        flows.add(new int[]{i, j, p});
                    }
                }
            }

            // flows 를 p로 오름차순 정렬한다.
            Collections.sort(flows, new FlowComparator());

            // int[n] parent 배열을 자기 자신으로 세팅한다.
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }

            // 크루스칼 count 해서 count 가 n-1 이면 종료
            int count = 0;
            long minValue = 0;
            for (int k = 0; k < flows.size(); k++) {
                if (count == n - 1) {
                    break;
                }

                int[] flow = flows.get(k);
                if (!find(flow[0], flow[1])) {
                    minValue += flow[2];
                    union(flow[0], flow[1]);
                    count++;
                }
            }

            return minValue;
        }

        private void union(int f1, int f2) {
            int p1 = getParent(f1);
            int p2 = getParent(f2);

            if (p1 < p2) {
                parent[p2] = p1;
            } else {
                parent[p1] = p2;
            }
        }

        private boolean find(int f1, int f2) {
            int p1 = getParent(f1);
            int p2 = getParent(f2);

            return p1 == p2;
        }

        private int getParent(int f) {
            if (parent[f] == f) {
                return f;
            }
            return getParent(parent[f]);
        }
    }

    static class FlowComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] f1, int[] f2) {
            return f1[2] - f2[2];
        }
    }
}

// 요약
// n개의 행성, 모든 행성 연결하면서 관리 비용은 최소화
// i == j 는 항상 0

// 19:53 -

//