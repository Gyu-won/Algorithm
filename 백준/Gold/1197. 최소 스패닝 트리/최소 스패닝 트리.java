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
            // v와 e를 입력받는다 (1-10000, 1-100000)
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            // e개의 edge를 입력받는다. (c는 1000000 절대값 이하)
            List<Edge> edges = new ArrayList<>();
            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(br.readLine(), " ");

                int n1 = Integer.parseInt(st.nextToken());
                int n2 = Integer.parseInt(st.nextToken());

                int distance = Integer.parseInt(st.nextToken());
                edges.add(new Edge(n1, n2, distance));
            }

            // edges를 정렬한다.
            Collections.sort(edges, new EdgeComparator());

            // 크루스칼 알고리즘
            int[] parent = new int[v];
            for (int i = 0; i < v; i++) {
                parent[i] = i;
            }

            int sum = 0;
            int count = 0;
            for (Edge edge : edges) {
                if (count == v - 1) {
                    break;
                }
                if (!find(parent, edge.n1, edge.n2)) {
                    count++;
                    sum += edge.distance;
                    union(parent, edge.n1, edge.n2);
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

        private void union(int[] parent, int n1, int n2) {
            int a = getParent(parent, n1);
            int b = getParent(parent, n2);

            if (a < b) {
                parent[b] = a;
            } else {
                parent[a] = b;
            }
        }

        private boolean find(int[] parent, int n1, int n2) {
            int a = getParent(parent, n1);
            int b = getParent(parent, n2);
            return a == b;
        }

        static class Edge {
            private final int n1;
            private final int n2;
            private final int distance;

            Edge(int n1, int n2, int distance) {
                this.n1 = n1 - 1;
                this.n2 = n2 - 1;
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
}

// 설계 시간:
// 풀이 시간:

//0. 문제요약
// mst를 구한다

//2. 시간복잡도: 

//3. 이 로직으로 예제 문제 손으로 풀기

//4. 경계값 체크 후 코드 구현
