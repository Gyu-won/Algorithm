import java.util.*;

class Solution {
    
    private static final int INF = 100000000;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        // 플로이드
        int[][] edges = new int[n+1][n+1];
        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= n; j++){
                if (i == j){
                    edges[i][j] = 0;
                    continue;
                }
                edges[i][j] = INF;
            }
        }
        
        for (int i = 0; i < fares.length; i++){
            int n1 = fares[i][0];
            int n2 = fares[i][1];
            int f = fares[i][2];
            
            edges[n1][n2] = f;
            edges[n2][n1] = f;
        }
        
        for (int k = 1; k <= n; k++){
            for (int i = 1; i <= n; i++){
                for (int j = 1; j <= n; j++){
                    if (edges[i][k] + edges[k][j] < edges[i][j]){
                        edges[i][j] = edges[i][k] + edges[k][j];
                    }
                }
            }
        }
        
        // k를 거쳐가는 거를 구해서 최소 구하기
        int minValue = Integer.MAX_VALUE;
        for (int k = 1; k <= n; k++){
            minValue = Math.min(minValue, edges[s][k] + edges[k][a] + edges[k][b]);
        }
        
        return minValue;
    }
}

// 요약
// 양방향 그래프, 합승 최적 택시 요금 구하기
// 같이 가는거는 한번만 침
// 처음부터 혼자 가도 됨
// n(3-200), s,a,b(1-n) 다른값
// fares는 (c, d, f)로 cd는 순서 없이 둘다 길로 인정


// O(n^3)

// 14:12 - 