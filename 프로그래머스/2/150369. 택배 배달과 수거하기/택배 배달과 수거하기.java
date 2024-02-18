import java.util.*;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        // 가장 먼 배달 지역으로 포인터를 설정
        int dPoint = -1, pPoint = -1;
        for (int i = 0; i < n; i++){
            if (deliveries[i] > 0){
                dPoint = i;
            }
            if (pickups[i] > 0){
                pPoint = i;
            }
        }
        
        long answer = 0;
        // 포인터가 >= 0 까지
        while (dPoint >= 0 || pPoint >= 0){
            // 두 포인터 중 큰 포인터+1 * 2 를 결과에 더하기
            answer += (Math.max(dPoint, pPoint) + 1) * 2;
            
            // 포인터에서 개수 빼기
            int left = cap;
            // 0보다 작아지면 0까지만 빼고 다음 포인터로 이동
            while (dPoint >= 0 && deliveries[dPoint] - left <= 0){
                left -= deliveries[dPoint];
                dPoint--;
            }
            if (dPoint >= 0){
                deliveries[dPoint] -= left;                
            }
            
            left = cap;
            while (pPoint >= 0 && pickups[pPoint] - left <= 0){
                left -= pickups[pPoint];
                pPoint--;
            }
            if (pPoint >= 0){
                pickups[pPoint] -= left;                
            }
                
         
        }        
        return answer;
    }
}

// 요약
// n 개의 집, cap개의 택배상자 한도 (1-1000,000) (1-50) deliveries 값 (0-50)
// 배달할 택배상자, 수거할 택배상자
// 트럭 하나로 배달과 수거를 마치고 다시 돌아오는 최소 이동 거리

// O()

// 10:38 - 