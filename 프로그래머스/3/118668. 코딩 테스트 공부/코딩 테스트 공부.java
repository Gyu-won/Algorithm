import java.util.*;

class Solution {
    // 0 <= alp, cop <= 150, problems 개수 <= 100
    
    private int targetAlp = 0;
    private int targetCop = 0;
    
    private Set<Integer>[][] visited;
    private int[][] problems;
    private List<Integer>[][] solvingProblems;
    
    public int solution(int alp, int cop, int[][] probs) {
        // 목표 점수 구하기: O(100)
        // 목표 점수까지 풀 수 있는 문제를 판별 정리: O(151 * 151 * 100) <= O(2.3 * 10^6)
        problems = probs;
        for (int i = 0; i < problems.length; i++) {
            targetAlp = Math.max(targetAlp, problems[i][0]);
            targetCop = Math.max(targetCop, problems[i][1]);
        }
        
        visited = new HashSet[targetAlp + 1][targetCop + 1];
        for (int i = 0; i <= targetAlp; i++) {
            for (int j = 0; j <= targetCop; j++) {
                visited[i][j] = new HashSet<>();
            }
        }
        
        solvingProblems = new ArrayList[targetAlp + 1][targetCop + 1];
        for (int i = 0; i <= targetAlp; i++) {
            for (int j = 0; j <= targetCop; j++) {
                solvingProblems[i][j] = new ArrayList<>();           
            }
        }
        
        for (int i = 0; i < problems.length; i++) {
            for (int a = problems[i][0]; a <= targetAlp; a++) {
                for (int c = problems[i][1]; c <= targetCop; c++) {
                    solvingProblems[a][c].add(i);
                }
            }
        }
         
        if (alp > targetAlp) {
            alp = targetAlp;
        }
        if (cop > targetCop) {
            cop = targetCop;
        }
        return calculateMinTime(alp, cop);
    }
    
    private int calculateMinTime(int alp, int cop) {
        PriorityQueue<Ability> pq = new PriorityQueue<>(new AbilityComparator());
        pq.add(new Ability(alp, cop, 0));
        visited[alp][cop].add(0);
        
        while(!pq.isEmpty()) {
            Ability ability = pq.poll();
            int currentAlp = ability.alp;
            int currentCop = ability.cop;
            int currentCost = ability.cost;
        
            if (currentAlp >= targetAlp && currentCop >= targetCop) {
                return currentCost;
            }
            
            for (int p : solvingProblems[currentAlp][currentCop]) {
                int nextAlp = Math.min(currentAlp + problems[p][2], targetAlp);
                int nextCop = Math.min(currentCop + problems[p][3], targetCop);
                int nextCost = currentCost + problems[p][4];
                
                if (!visited[nextAlp][nextCop].contains(nextCost)) {
                    pq.add(new Ability(nextAlp, nextCop, nextCost));
                    visited[nextAlp][nextCop].add(nextCost);
                }
            }
            int nextCost = currentCost + 1;
            int nextAlp = Math.min(currentAlp + 1, targetAlp);
            int nextCop = Math.min(currentCop + 1, targetCop);
            if (!visited[nextAlp][currentCop].contains(nextCost)) {
                pq.add(new Ability(nextAlp, currentCop, nextCost));
                visited[nextAlp][currentCop].add(nextCost);
            }
            
            if (!visited[currentAlp][nextCop].contains(nextCost)) {
                pq.add(new Ability(currentAlp, nextCop, nextCost));
                visited[currentAlp][nextCop].add(nextCost);
            }
        }
        return 0;
    }
    
    private class Ability {
        private final int alp;
        private final int cop;
        private final int cost;
        private final int total;

        Ability(int alp, int cop, int cost) {
            this.alp = alp;
            this.cop = cop;
            this.cost = cost;
            total = alp + cop;
        }
    }
    
    private class AbilityComparator implements Comparator<Ability> {
        @Override
        public int compare(Ability a1, Ability a2) {
            if (a1.cost == a2.cost) {
                return a2.total - a1.total;
            }
            return a1.cost - a2.cost;
        }
    }
}

// 최적의 시간을 계산
// 넣기 전에 넣은 것 중 나보다 큰거 있으면 안넣기

// 09:08 -