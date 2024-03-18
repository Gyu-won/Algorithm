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
        // n 입력 (1-100)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // stars 입력
        StringTokenizer st;
        double[][] stars = new double[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                stars[i][j] = Double.parseDouble(st.nextToken());
            }
        }

        // distance 구하기 (5050)
        List<Route> distances = calculateDistance(n, stars);

        // distance 정렬 nlogn
        Collections.sort(distances, new RouteComparator());

        // mst
        System.out.println(mst(n, distances));
    }

    private static double mst(int n, List<Route> routes) {
        int[] stars = new int[n];
        for (int i = 0; i < n; i++) {
            stars[i] = i;
        }

        double distance = 0;
        for (Route route : routes) {
            if (!find(route.from, route.to, stars)) {
                union(route.from, route.to, stars);
                distance += route.distance;

                if (isExit(stars)) {
                    break;
                }
            }
        }
        return distance;
    }

    private static boolean isExit(int[] stars) {
        for (int i = 0; i < stars.length; i++) {
            if (stars[i] > 0) {
                return false;
            }
        }
        return true;
    }

    private static void union(int from, int to, int[] stars) {
        int parent1 = getParent(from, stars);
        int parent2 = getParent(to, stars);

        if (parent1 < parent2) {
            stars[parent2] = parent1;
        } else {
            stars[parent1] = parent2;
        }
    }

    private static boolean find(int from, int to, int[] stars) {
        int parent1 = getParent(from, stars);
        int parent2 = getParent(to, stars);

        return parent1 == parent2;
    }

    private static int getParent(int star, int[] stars) {
        if (stars[star] == star) {
            return star;
        }
        return getParent(stars[star], stars);
    }


    private static List<Route> calculateDistance(int n, double[][] stars) {
        List<Route> routes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double distance = Math.sqrt(
                        Math.pow(stars[i][0] - stars[j][0], 2) + Math.pow(stars[i][1] - stars[j][1], 2));
                routes.add(new Route(i, j, distance));
            }
        }
        return routes;
    }

    private static class Route {
        private final int from;
        private final int to;
        private final double distance;

        Route(int from, int to, double distance) {
            this.from = from;
            this.to = to;
            this.distance = distance;
        }
    }

    private static class RouteComparator implements Comparator<Route> {
        @Override
        public int compare(Route r1, Route r2) {
            if (r1.distance < r2.distance) {
                return -1;
            }
            return 1;
        }
    }
}

// 21:49 -

// 모든 별 이어져야함, mst