import java.util.*;

class Solution {
    
    private static int n;
    private static int maxSheep = 0;
    private static boolean[] visited;
    private static List<Integer>[] children;
    
    public int solution(int[] info, int[][] edges) {
        
        n = info.length;
        
        visited = new boolean[n];
        
        // children을 생성
        children = new ArrayList[n];
        for (int i = 0; i < n; i++){
            children[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < edges.length; i++){
            int[] edge = edges[i];
            children[edge[0]].add(edge[1]);
        }
        
        // dfs 
        visited[0] = true;
        dfs(1, 0, info);
        
        return maxSheep;
    }
    
    private void dfs(int sheep, int wolf, int[] info){
        if (sheep == wolf){
            return;
        }
        
        maxSheep = Math.max(maxSheep, sheep);
        for (int i = 0; i < n; i++){
            if (visited[i]){
                for (int child: children[i]){
                    if (!visited[child]){
                        visited[child] = true;
                        if (info[child] == 0){
                            dfs(sheep+1, wolf, info);                            
                        }else{
                            dfs(sheep, wolf+1, info);
                        }
                        visited[child] = false;
                    }
                }
            }
        }
    }
}

// info길이 0-17 / 0이면 양 1이면 늑대

// O(2^n)

// 현재 위치에서 visited 한애들을 모두 갈 수 있음

// 10:30 - 