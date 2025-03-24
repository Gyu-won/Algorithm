import java.util.*;

class Solution {

    private final int[] dx = new int[]{0, 0, -1, 1};
    private final int[] dy = new int[]{-1, 1, 0, 0};
    
    private int n, m;
    private int[][] queries;
    private long total = 0;
    
    // queries <= 2*10^5
    public long solution(int nInput, int mInput, int x, int y, int[][] queriesInput) {
        n = nInput;
        m = mInput;
        queries = queriesInput;
        
        int left = y;
        int right = y;
        int top = x;
        int bottom = x;
        return calculate(left, right, top, bottom);
    }
    
    private long calculate(int left, int right, int top, int bottom) {
        for(int i = queries.length - 1; i >= 0; i--) {   
            int[] query = queries[i];
            if (query[0] == 0) {
                if (left != 0) {
                    left += query[1];
                    if (left > m-1) {
                        return 0;
                    }    
                } 
                right = Math.min(right + query[1], m-1);
            } else if (query[0] == 1) {
                if (right != m-1) {
                    right -= query[1];
                    if (right < 0) {
                        return 0;
                    }    
                } 
                left = Math.max(left - query[1], 0);
            } else if (query[0] == 2) {
                if (top != 0) {
                    top += query[1];
                    if (top > n-1) {
                        return 0;
                    }    
                } 
                bottom = Math.min(bottom + query[1], n - 1);
            } else {
                if (bottom != n-1) {
                    bottom -= query[1];
                    if (bottom < 0) {
                        return 0;
                    }    
                } 
                top = Math.max(top - query[1], 0);
            }
        }
        return ((long) (right - left) + 1) * ((long) (bottom - top) + 1);
    }
}

// 최종 도착지 기준 개수 구하기
// 완탐: n * m * queries

// 모든 점을 방문 n*m -> 방문하지 않고 되는지 안된느지 구별 해야함
    // queries 하나씩 실행 O(2*10^5)

// dfs (toDown) + stack