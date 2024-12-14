import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int left = 100000;
        int right = 1;
        for (int i = 0; i < diffs.length; i++) {
            if (diffs[i] < left) left = diffs[i];
            if (diffs[i] > right) right = diffs[i];
        }
        
        while (left < right){
            int middle = (left + right) / 2;
            
            long time = calculateTime(middle, diffs, times);
            
            if (time <= limit){
                right = middle;
            }
            else{
                left = middle + 1;
            }
        }
        return left;
    }
    
    private static long calculateTime(int level, int[] diffs, int[] times){
        long totalTime = 0;
        for (int i = 0; i < diffs.length; i++){
            if (diffs[i] <= level) {
                totalTime += times[i];   
            }else {
                int wrongTime = (diffs[i] - level) * (times[i] + times[i-1]);
                totalTime += (wrongTime + times[i]);
            }
        }
        return totalTime;
    }
}

// diff 최솟값, 최대값 구하기
// 최댓값 최솟값 범위에서 이분탐색 O(log10,000)
    // 각 값에 대해서 전체 소요 시간 계산
    // limit 통과 여부 확인
    // currentPos 값 조정

// diff <= level -> time_cur 시간 소요
// diff > level -> (diff -level) * (time_cur + time_prev) + time_cur

// 결과: limit 안에 모든 퍼즐을 풀기위한 최소 level 값
// O(nlog(100,000)) 이분탐색

