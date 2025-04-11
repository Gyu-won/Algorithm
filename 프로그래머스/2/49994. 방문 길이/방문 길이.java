import java.util.*;

class Solution {
    
    private final int[] dr = new int[] {-1, 1, 0, 0};
    private final int[] dc = new int[] {0, 0, 1, -1};
    
    private final int minR = 0;
    private final int minC = 0;
    private final int maxR = 10;
    private final int maxC = 10;
    private final Set<Integer> route = new HashSet<>();
    
    int currentR = 5;
    int currentC = 5;
    
    public int solution(String dirs) {
        for (char d: dirs.toCharArray()) {
            move(toDirection(d));
        }
        return route.size();
    }
    
    private int toDirection(char d) {
        if (d == 'U') {
            return 0;
        }
        if (d == 'D') {
            return 1;
        }
        if (d == 'R') {
            return 2;
        }
        if (d == 'L') {
            return 3;
        }
        return -1;
    }
    
    private void move(int d) {
        int nextR = currentR + dr[d];
        int nextC = currentC + dc[d];
        
        if (!outOfBound(nextR, nextC)) {
            route.add(createKey(currentR, currentC, nextR, nextC));
            currentR = nextR;
            currentC = nextC;
        }
    }
    
    private boolean outOfBound(int r, int c) {
        return r < minR || r > maxR || c < minC || c > maxC;
    }
    
    private int createKey(int r1, int c1, int r2, int c2) {
        int firstKey = r1 * 11 + c1;
        int secondKey = r2 * 11 + c2;
        if (firstKey > secondKey) {
            return secondKey * 1000 + firstKey;
        }
        return firstKey * 1000 + secondKey;
    }
}

// 이동
// 가로 0-10, 세로 1-11 로 key 값 만들기 (131 최대, 더 작은걸 앞에 붙이기)
// set으로 처리