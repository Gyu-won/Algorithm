import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder result = new StringBuilder();

        while (true) {
            // 0, 0 있으면 완전히 종료
            st = new StringTokenizer(br.readLine(), " ");

            // m, n 입력 (1-200,000) (m-1 - 200,000)
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            if (m == 0 && n == 0) {
                break;
            }

            // routes 입력
            int totalCost = 0;
            List<Route> routes = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine(), " ");

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());

                routes.add(new Route(x, y, z));
                totalCost += z;
            }

            // routes 정렬
            routes.sort(Comparator.comparing(Route::getDistance));

            // mst
            result.append(totalCost - mst(m, routes));
            result.append("\n");
        }
        System.out.println(result.toString().trim());
    }

    private static int mst(int m, List<Route> routes) {
        int[] parent = new int[m];
        for (int i = 0; i < m; i++) {
            parent[i] = i;
        }

        int idx = 0, cost = 0, cnt = 0;
        while (cnt < m - 1) {
            Route route = routes.get(idx++);
            if (!find(route.town1, route.town2, parent)) {
                cost += route.distance;
                cnt++;
                union(route.town1, route.town2, parent);
            }
        }
        return cost;
    }

    private static int getParent(int town1, int[] parent) {
        if (parent[town1] == town1) {
            return town1;
        }
        return getParent(parent[town1], parent);
    }

    private static void union(int town1, int town2, int[] parent) {
        int parent1 = getParent(town1, parent);
        int parent2 = getParent(town2, parent);

        if (parent1 < parent2) {
            parent[parent2] = parent1;
        } else {
            parent[parent1] = parent2;
        }
    }


    private static boolean find(int town1, int town2, int[] parent) {
        int parent1 = getParent(town1, parent);
        int parent2 = getParent(town2, parent);
        return parent1 == parent2;
    }

    private static class Route {
        private final int town1;
        private final int town2;
        private final int distance;

        Route(int town1, int town2, int distance) {
            this.town1 = town1;
            this.town2 = town2;
            this.distance = distance;
        }

        private int getDistance() {
            return this.distance;
        }
    }
}

// 불이 켜진길로 왕래 해야한다.
// 모든 도로 연결하는데 거리가 최소가 되게끔 -> mst