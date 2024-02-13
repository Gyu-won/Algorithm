import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        // 숫자를 찾아가며 이분 탐색
        
        int start = 1;
        int end = 200000000;
        
        while (start <= end){
            int mid = (start + end)/ 2;
            if (canCross(mid, stones, k)){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        return end;
    }
    
    public boolean canCross(int n, int[] stones, int k){
        int cnt = 0;
        for (int i = 0; i < stones.length; i++){   
            if (stones[i] - n < 0) cnt++;
            else cnt = 0;
            if (cnt == k) return false;
        }
        return true;
    }
}

// 요약
// 디딤돌 밟을 때마다 1씩 줄어듦, 0이면 못밟음, 이러면 다음칸으로 건너뜀
// 한명씩 다 건너고 건너기 시작함
// 최대 몇명까지 건널 수 있는지 (친구수는 무제한)
// stones 크기 (1-200,000) 값 (1-200,000,000), k 는 (1- stones 길이)

// 결국 연속된 0 이 k개 일때까지 문제네
// 징검다리 값, 위치로 List<Stone> 저장
// 값으로 오름차순 정렬


// 22:41 - 

// O()