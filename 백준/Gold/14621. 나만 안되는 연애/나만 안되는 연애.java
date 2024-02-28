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
        // n, m 을 입력 (2-1000) (1-10000)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // colleges를 입력
        String[] colleges = new String[n + 1];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= n; i++) {
            colleges[i] = st.nextToken();
        }

        // edges를 입력받을 때 남초 - 여초만 추가
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            if (!colleges[n1].equals(colleges[n2])) {
                edges.add(new Edge(n1, n2, time));
            }
        }

        // 정렬
        Collections.sort(edges, new EdgeComparator());

        // 크루스칼
        System.out.println(kruskal(n, edges));
    }

    private static int kruskal(int n, List<Edge> edges) {
        int minDistance = 0, count = 0, idx = 0;

        int[] parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        while (idx < edges.size()) {
            Edge edge = edges.get(idx++);
            if (!find(edge.n1, edge.n2, parent)) {
                count++;
                minDistance += edge.time;
                union(edge.n1, edge.n2, parent);
            }

            if (count == n - 1) {
                return minDistance;
            }
        }
        return -1;
    }

    private static void union(int n1, int n2, int[] parent) {
        int p1 = getParent(n1, parent);
        int p2 = getParent(n2, parent);

        if (p1 < p2) {
            parent[p2] = p1;
        } else {
            parent[p1] = p2;
        }
    }

    private static int getParent(int node, int[] parent) {
        if (node == parent[node]) {
            return node;
        }
        return getParent(parent[node], parent);
    }

    private static boolean find(int n1, int n2, int[] parent) {
        int p1 = getParent(n1, parent);
        int p2 = getParent(n2, parent);
        return p1 == p2;
    }

    private static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge e1, Edge e2) {
            return e1.time - e2.time;
        }
    }

    private static class Edge {
        private final int n1;
        private final int n2;
        private final int time;

        Edge(int n1, int n2, int time) {
            this.n1 = n1;
            this.n2 = n2;
            this.time = time;
        }
    }
}

// 남초 M, 여초 W