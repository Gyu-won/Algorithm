class Solution {
        int maxAlp, maxCop;
        int[][] problems;
        Integer[][] DP;

        int 완전탐색(int alp, int cop) {
            if (alp >= maxAlp && cop >= maxCop) return 0;
            alp = Math.min(maxAlp, alp);
            cop = Math.min(maxCop, cop);
            if (DP[alp][cop] != null) return DP[alp][cop];
            int cost = Integer.MAX_VALUE;
            if (alp < maxAlp) cost = Math.min(cost, 1 + 완전탐색(alp + 1, cop));
            if (cop < maxCop) cost = Math.min(cost, 1 + 완전탐색(alp, cop + 1));
            for (int[] p : problems)
                if (p[0] <= alp && p[1] <= cop) {
                    if ((alp >= maxAlp && p[3] == 0) || (cop >= maxCop && p[2] == 0)) continue;
                    cost = Math.min(cost, p[4] + 완전탐색(alp + p[2], cop + p[3]));
                }
            return DP[alp][cop] = cost;
        }

        public int solution(int alp, int cop, int[][] problems) {
            this.problems = problems;
            maxAlp = maxCop = 0;
            for (int[] p : problems) {
                maxAlp = Math.max(maxAlp, p[0]);
                maxCop = Math.max(maxCop, p[1]);
            }
            DP = new Integer[maxAlp + 1][maxCop + 1];
            return 완전탐색(alp, cop);
        }
    }