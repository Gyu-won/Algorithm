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
        // n과 m을 입력받는다 (1-1000), (1-1000)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // int[][] points에 저장 n개의 x, y를 입력받는다 (0-1000000)
        int[][] points = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            points[i][0] = Integer.parseInt(st.nextToken());
            points[i][1] = Integer.parseInt(st.nextToken());
        }

        // 거리를 계산한다.
        List<Route> routes = calculateDistance(n, points);
//        for (int i = 0; i < routes.size(); i++) {
//            System.out.println(routes.get(i).n1 + " / " + routes.get(i).n2 + " / " + routes.get(i).distance);
//        }

        // m 개의 현재 경로를 입력받는다.
        int[] parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            union(n1, n2, parent);
        }

        System.out.print(String.format("%.2f", calculateMinDistance(n, m, parent, routes)));
    }

    private static boolean find(int n1, int n2, int[] parent) {
        int p1 = getParent(n1, parent);
        int p2 = getParent(n2, parent);
        return p1 == p2;
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

    private static int getParent(int n, int[] parent) {
        if (n == parent[n]) {
            return n;
        }
        return getParent(parent[n], parent);
    }

    private static double calculateMinDistance(int n, int m, int[] parent, List<Route> routes) {
        double cost = 0.0;
        for (int i = 0; i < routes.size(); i++) {
            Route route = routes.get(i);
            if (!find(route.n1, route.n2, parent)) {
                cost += route.distance;
                union(route.n1, route.n2, parent);
                m++;
            }
        }
        return cost;
    }

    private static List<Route> calculateDistance(int n, int[][] points) {
        List<Route> routes = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            int x1 = points[i][0];
            int y1 = points[i][1];

            for (int j = i + 1; j <= n; j++) {
                int x2 = points[j][0];
                int y2 = points[j][1];

                double distance = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
                routes.add(new Route(i, j, distance));
            }
        }

        Collections.sort(routes, new RouteComparator());

        return routes;
    }

    static class RouteComparator implements Comparator<Route> {
        @Override
        public int compare(Route r1, Route r2) {
            if (r1.distance <= r2.distance) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    static class Route {
        private final int n1;
        private final int n2;
        private final double distance;

        Route(int n1, int n2, double distance) {
            this.n1 = n1;
            this.n2 = n2;
            this.distance = distance;
        }


    }
}

// 요약

// 2초

// 21:43