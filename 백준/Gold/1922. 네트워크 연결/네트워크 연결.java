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

            // m을 입력받는다.
            int m = Integer.parseInt(br.readLine());

            // List<Edge> edges를 입력받는다
            List<Edge> edges = new ArrayList<>();

            // a==b 면 추가 안함, count 개수 계산
            StringTokenizer st;
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine(), " ");

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                if (a != b) {
                    edges.add(new Edge(a, b, d));
                }
            }

            Collections.sort(edges, new EdgeComparator());

            // mst
            int[] parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }

            int sum = 0;
            int count = 0;
            for (Edge edge : edges) {
                if (count == n - 1) {
                    break;
                }
                if (!find(parent, edge.a, edge.b)) {
                    sum += edge.distance;
                    count++;
                    union(parent, edge.a, edge.b);
                }
            }
            return sum;
        }

        private int getParent(int[] parent, int node) {
            if (parent[node] == node) {
                return node;
            }
            return getParent(parent, parent[node]);
        }

        private boolean find(int[] parent, int e1, int e2) {
            int a = getParent(parent, e1);
            int b = getParent(parent, e2);

            return a == b;
        }

        private void union(int[] parent, int e1, int e2) {
            int a = getParent(parent, e1);
            int b = getParent(parent, e2);

            if (a < b) {
                parent[b] = a;
            } else {
                parent[a] = b;
            }
        }
    }

    static class Edge {
        private final int a;
        private final int b;
        private final int distance;

        Edge(int a, int b, int distance) {
            this.a = a - 1;
            this.b = b - 1;
            this.distance = distance;
        }
    }

    static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge e1, Edge e2) {
            return e1.distance - e2.distance;
        }
    }
}

// 설계 시간: 20:12 -
// 풀이 시간:

//0. 문제요약
// 모두 연결하는데 최소 비용으로 연결하는 mst

//2. 시간복잡도: 

//3. 이 로직으로 예제 문제 손으로 풀기

//4. 경계값 체크 후 코드 구현
