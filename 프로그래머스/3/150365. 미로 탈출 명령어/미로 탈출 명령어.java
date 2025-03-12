import java.util.*;

class Solution {
    
    private final int[] dr = new int[] {1, 0, 0, -1};
    private final int[] dc = new int[] {0, -1, 1, 0};
    private final char[] directions = new char[]{'d', 'l', 'r', 'u'};
    
    private int currentR;
    private int currentC;
    private int maxN;
    private int maxM;
    
    // n <= 50, m <= 50, k <= 2500
    public String solution(int n, int m, int x, int y, int r, int c, int k)     {
        // 1. e까지 가야하는 최단경로 구하기 (차로 구하기)
        int diffR = r - x;
        int diffC = c - y;
        maxN = n;
        maxM = m;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        if (diffR > 0) {
            for (int i = 0; i < diffR; i++) {
                pq.add(0);    
            }
        } else if (diffR < 0) {
            for (int i = 0; i < -1 * diffR; i++) {
                pq.add(3);    
            }
        }
        
        if (diffC > 0) {
            for (int i = 0; i < diffC; i++) {
                pq.add(2);    
            }
        } else if (diffC < 0) {
            for (int i = 0; i < -1 * diffC; i++) {
                pq.add(1);    
            }
        }
        
        // 2. impossible 확인 (홀수면 홀수, 짝수면 짝수인지 판단)
        if (pq.size() % 2 != k % 2 || pq.size() > k) {
            return "impossible";
        }
        
        // 3. 나머지 채워넣기
        // 추가로 넣어야하는 개수 구하기
        int numberOfAdd = (k - pq.size()) / 2;
        
        // numberOfAdd가 0 이전까지 O(4 * k)
        currentR = x;
        currentC = y;
        StringBuilder result = new StringBuilder();
        
        // 넣어야할 것, d-> u 까지 보면서 넣어야할게 더 작거나 같으면 넣기
        // 더 크면 넣을 수 있는지 보고 넣을 수 있으면 넣기, 반대꺼 insert, idx-- 
        while (numberOfAdd > 0) { 
            for (int d = 0; d < 4; d++) {
                if (!pq.isEmpty()) {
                    if (d < pq.peek()) {
                        if (isPossible(d)) {
                            move(d);    
                            result.append(directions[d]);
                            pq.add(3 - d);
                            numberOfAdd--;
                            break;
                        } else {
                            continue;
                        }
                    } else {
                        int direction = pq.poll();
                        move(direction);
                        result.append(directions[direction]);
                        break;
                    }    
                } else{
                    if (isPossible(d)) {
                        move(d);
                        result.append(directions[d]);
                        pq.add(3 - d);
                        numberOfAdd--;
                        break;
                    } else {
                        continue;
                    }
                }
            }  
        }
            
        // 나머지 넣기 
        while(!pq.isEmpty()) {
            result.append(directions[pq.poll()]);
        }
        
        return result.toString();
    }
    
    private boolean isPossible(int d) {
        int nextR = currentR;
        int nextC = currentC;
        nextR += dr[d];
        nextC += dc[d];
        
        return nextR > 0 && nextC > 0 && nextR <= maxN && nextC <= maxM;
    }
    
    private void move(int d) {
        currentR += dr[d];
        currentC += dc[d];
    }
}

// 0821 -
// k 후 r, c에 있으면 됨
// 경로들 중 사전 순으로 가장 빠름
// 아래 -> 왼쪽 -> 오른쪽 -> 위쪽