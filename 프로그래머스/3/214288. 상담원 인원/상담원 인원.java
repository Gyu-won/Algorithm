import java.util.*;

class Solution {
    private int minWaitTime = Integer.MAX_VALUE;
    private int[] types;
    private int[][] requests;
    private PriorityQueue<Integer>[] pqs;
    
    // k개의 상담 유형 <=5, n 명의 멘토<=20, reqs<=300
    public int solution(int k, int n, int[][] reqs) {
        types = new int[k + 1];
        requests = reqs;
        pqs = new PriorityQueue[k+1];
        for (int i = 1; i <= k; i++) {
            pqs[i] = new PriorityQueue<>();
        }
        
        // 모든 경우 조합
        dfs(n, k, 1);
        
        return minWaitTime;
    }
    
    private void dfs(int rest, int k, int idx) {
        if (idx == k) {
            types[idx] = rest;
            int waitTime = calculate();
            minWaitTime = Math.min(minWaitTime, waitTime);
            return;
        }
        
        for (int i = 1; i <= rest - (k - idx); i++) {
            types[idx] = i;
            dfs(rest - i, k, idx+1);
        }
    }
    
    // 상담시간 구하기: O(300 * log n)
    private int calculate() {
        int waitTime = 0;
        for (int[] req: requests) {
            PriorityQueue<Integer> pq = pqs[req[2]];
            if (pq.isEmpty()) {
                pq.add(req[0] + req[1]);
                continue;
            }
            if (pq.peek() <= req[0]) {
                pq.poll();
                pq.add(req[0] + req[1]);
            } else {
                if (pq.size() < types[req[2]]) {
                    pq.add(req[0] + req[1]);
                } else {
                    int endTime = pq.poll();
                    waitTime += (endTime - req[0]);
                    pq.add(endTime + req[1]);
                }
            }
        }
        
        for (int i = 1; i < pqs.length; i++) {
            pqs[i].clear();
        }
        
        return waitTime;
    }
    
    // pq.empty거나 제일 위에것보다 내가 더 크면 빼고, 추가
    // 위에것보다 내가 더 작으면 size가 types[idx] 보다 작으면 추가, 같으면 빼고 차이만큼 기다린 후 추가
}

// 기다리는 시간이 최소
