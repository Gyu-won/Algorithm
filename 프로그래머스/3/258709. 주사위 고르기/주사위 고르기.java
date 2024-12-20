import java.util.*;

class Solution {
    
    private static int n, maxNumberOfWinning = 0;
    private static boolean[] visited;
    private static int[] result, answer;
    private static int[][] dice;
    public int[] solution(int[][] input) {
        
        dice = input;
        n = dice.length;
        visited = new boolean[n];
        answer = new int[n/2];
        combination(0, 0);
        
        return answer;
    }
    
    private static void combination(int k, int cnt) {
        if (cnt == n/2) {
            List<Integer> aDiceList = new ArrayList<>();
            List<Integer> bDiceList = new ArrayList<>();
            List<Integer> aSumList = new ArrayList<>();
            List<Integer> bSumList = new ArrayList<>();
            
            for (int i = 0; i < n; i++) {
                if (visited[i]) {
                    aDiceList.add(i);
                }else {
                    bDiceList.add(i);
                }
            }
            
            calculateSum(0, 0, aDiceList, aSumList);
            calculateSum(0, 0, bDiceList, bSumList);
            
            Collections.sort(bSumList);
            
            int numberOfWinning = 0;
            for (int aSum: aSumList) {
                numberOfWinning += binarySearch(aSum, bSumList);
            }
            
            if (maxNumberOfWinning < numberOfWinning) {
                maxNumberOfWinning = numberOfWinning;
                for (int i = 0; i < aDiceList.size(); i++) {
                    answer[i] = aDiceList.get(i) + 1;
                }
            }
            return;
        }
        for (int i = k; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                combination(i+1, cnt+1);
                visited[i] = false;
            }
        }
    }
    
    private static int binarySearch(int value, List<Integer> list) {
        int middle, start = 0, end = list.size() - 1;
        
        while(start <= end) {
            middle = (start + end) / 2;
            if (value <= list.get(middle)) {
                end = middle - 1;
            } else{
                start = middle + 1;
            }
        }
        return start;
    }
    
    private static void calculateSum(int cnt, int sum, List<Integer> diceList, List<Integer> sumList) {
        if (cnt == n/2) {
            sumList.add(sum);
            return;
        }
        
        int diceNumber = diceList.get(cnt);
        for (int i = 0; i < 6; i++) {
            calculateSum(cnt+1, sum + dice[diceNumber][i], diceList, sumList);
        }
    }
}

// 조합 나누기 nCn/2 (visited로 계산)
    // 해당 조합에 대해 합 계산 O(6^n)
        // 재귀 형태로 돌면서 visited[n] 에 따라 값 계산 

// O(nCn/2 * 6^n)
// A가 나올 수 있는합, B가 나올 수 있는 합 따로 구하기 O(6^n/2)
// 정렬 후 이분 탐색으로 A가 정해졌을때 A보다 작은 B 값 찾기 O(nlogn)


// 결과: 가장 확률이 높은 주사위 조합을 번호로 오름차순 (int) 