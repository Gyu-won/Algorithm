import java.util.*;

class Solution {
    public int solution(int[] food_times, long k) {        
        // n 구하기
        int n = food_times.length;
        
        // food_times를 오름차순 정렬
        int[] times = new int[n];
        for (int i = 0; i < n; i++){
            times[i] = food_times[i];
        }
        Arrays.sort(times);
        
        // n > 0 이고 k - n >= 0일때까지
        int cnt = 0, idx = 0;
        while(n > 0 && k - n >= 0){
            // k update
            k -= n;
            
            cnt++;
            
            // cnt가 food_times에 앞에 원소랑 같으면 idx++, n을 개수만큼 줄임
            int count = 0;
            while (idx < times.length && cnt == times[idx]){
                idx++;
                count++;
            }
            n -= count;
        }
            
        // n 이 0이면 -1
        if (n <= 0){
            return -1;
        }
        
        // cnt 보다 큰값 중 k+1 번째 값의 index 리턴
        int count = 0;
        idx = 0;
        for (; idx < food_times.length; idx++){
            if (food_times[idx] > cnt){
                count++;
                if (count == k+1){
                    break;
                }
            }
        }
        return idx+1;
            
    }
}

// food_times (1-200,000), 원소 값은 (1-100,000,000)
// k (1-2*10^13) : long

// O(logn)

// n 개의 음식, 순서대로 먹음
// 1초동안 음식 먹고 다음 음식 먹는다, 회전판 도는데 시간은 없다
// k 초 후 어디를 먹고 있을지, 없으면 -1