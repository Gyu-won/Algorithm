import java.util.*;

class Solution {
    // 0 <= alp, cop <= 150, problems 개수 <= 100

    private int targetAlp = 0;
    private int targetCop = 0;

    private Map<Integer, List<int[]>> addedMap = new HashMap<>();
    private int[][] problems;
    private List<Integer>[][] solvingProblems;
    private PriorityQueue<Ability> pq;

    public int solution(int alp, int cop, int[][] probs) {
        // 목표 점수 구하기: O(100)
        // 목표 점수까지 풀 수 있는 문제를 판별 정리: O(151 * 151 * 100) <= O(2.3 * 10^6)
        problems = probs;
        for (int i = 0; i < problems.length; i++) {
            targetAlp = Math.max(targetAlp, problems[i][0]);
            targetCop = Math.max(targetCop, problems[i][1]);
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
        pq = new PriorityQueue<>(new AbilityComparator());
        pq.add(new Ability(alp, cop, 0));

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
                
                addIfValid(nextAlp, nextCop, nextCost);
            }
            int nextCost = currentCost + 1;
            int nextAlp = Math.min(currentAlp + 1, targetAlp);
            int nextCop = Math.min(currentCop + 1, targetCop);
            
            addIfValid(nextAlp, currentCop, nextCost);
            addIfValid(currentAlp, nextCop, nextCost);
        }
        return 0;
    }
    
    private void addIfValid(int alp, int cop, int cost) {
        boolean isAdded = false;
        for (int[] added: addedMap.getOrDefault(cost, new ArrayList<>())) {
            if (alp <= added[0] && cop <= added[1]) {
                isAdded = true;
                break;
            }
        }
        if (!isAdded) {
            pq.add(new Ability(alp, cop, cost));
            List<int[]> addedList = addedMap.getOrDefault(cost, new ArrayList<>());
            addedList.add(new int[] {alp, cop});
            addedMap.put(cost, addedList);
        }
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