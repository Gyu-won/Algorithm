import java.util.*;

class Solution {
    // n<= 5*10^4, paths<= 2*10^5
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        // path 연결
        List<Route>[] routes = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            routes[i] = new ArrayList<>();
        }
        for (int[] path: paths) {
            routes[path[0]].add(new Route(path[1], path[2]));
            routes[path[1]].add(new Route(path[0], path[2]));
        }
        
        // int[n] point: gates(1), summit(2) 구분
        int[] points = new int[n + 1];
        for (int i = 0; i < gates.length; i++) {
            points[gates[i]] = 1;
        }
        for (int i = 0; i < summits.length; i++) {
            points[summits[i]] = 2;
        }
        
        // gates 반복하며 하나씩 시작 O(n)
        int minDistance = Integer.MAX_VALUE;
        int summit = Integer.MAX_VALUE;
        Set<Integer> visited = new HashSet<>();
        for (int gate: gates) {
            PriorityQueue<Route> pq = new PriorityQueue<>(new RouteComparator());
            pq.add(new Route(gate, 0));
            
            while(!pq.isEmpty()) {
                Route route = pq.poll();
                if (visited.contains(route.to)) {
                    continue;
                }
                
                visited.add(route.to);
                // summits 인지 확인: O(1)     
                if (points[route.to] == 2) {
                    if (minDistance > route.distance || (minDistance == route.distance && summit > route.to)) {
                        minDistance = route.distance;
                        summit = route.to;
                    }
                    break;
                }
                
                for (Route next: routes[route.to]) {
                    // gates인지 확인(set): O(1)
                    if (!visited.contains(next.to) && points[next.to] != 1) {
                        pq.add(new Route(next.to, Math.max(route.distance, next.distance)));
                    }
                }
            }
            visited.clear();
        }
        
        return new int[]{summit, minDistance};
    }
    
    private class Route {
        private final int to;
        private final int distance;
        
        Route (int to, int distance) {
            this.to = to;
            this.distance = distance;
        }
    }
    
    private class RouteComparator implements Comparator<Route> {
        @Override
        public int compare(Route r1, Route r2) {
            if (r1.distance == r2.distance) {
                return r1.to - r2.to;
            }
            return r1.distance - r2.distance;
        }
    }
}

// n 개의 지점, 쉼터, 산봉우리는 휴식
// 산봉우리 한곳만 방문, 출입구도 처음과 끝에만, intensity 최소, 산봉우리 제일 작은 것
// 갈 수 있는 곳이 등산로밖에 없을 수도 
