class Solution {
    // 12시, 00시
    private static final int MIDNIGHT = 0;
    private static final int NOON = 12 * 60 * 60;
    
    // 초 단위 속도 구하기
    private static final double HOUR_SPEED = 360.0 / (60 * 60 * 12);
    private static final double MINUTE_SPEED = 360.0 / (60 * 60);
    private static final double SECOND_SPEED = 360.0 / 60;
    
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int count = 0;
        int startTime = h1 * 3600 + m1 * 60 + s1;
        int endTime = h2 * 3600 + m2 * 60 + s2;
        
        // 시작 위치
        double startHour = (startTime * HOUR_SPEED) % 360;
        double startMinute = (startTime * MINUTE_SPEED) % 360;
        double startSecond = (startTime * SECOND_SPEED) % 360;
        double endHour = (endTime * HOUR_SPEED) % 360;
        double endMinute = (endTime * MINUTE_SPEED) % 360;
        double endSecond = (endTime * SECOND_SPEED) % 360;
        
        // 시작, 끝이 같은 분인 경우    
        if (h1 == h2 && m1 == m2) {
            // 시작일 때 초침이 분침보다 작거나 같고 끝일 때 크거나 같으면 +1
            if (startSecond <= startMinute && endSecond >= endMinute) {
                count++;
            }
            
            // 시작일 때 초침이 시침보다 작거나 같고 끝일 때 크거나 같으면 +1
            if (startSecond <= startHour && endSecond >= endHour) {
                count++;
            }
        
        } else{ // 시작, 끝이 다른 분인 경우
            // 시작일 때 초침이 분침 or 시침보다 작으면 +1
            if (startSecond <= startMinute) {
                count++;
            }
            if (startSecond <= startHour) {
                count++;
            }
            if (endSecond >= endMinute) {
                count++;
            }
            if (endSecond >= endHour) {
                count++;
            }
            
            // 분마다 +2
            int minuteDifference = ((endTime - (endTime % 60)) - (startTime - (startTime % 60) + 60)) / 60;
            count += minuteDifference * 2;
            
            // o시 59분은 분침은 안지남
            count -= (h2 - h1);
            
            // 11시 59분은 시침을 안지남
            if (startTime < NOON && endTime >= NOON) {
                count--;
            }
        }
        
        // 12시나 00시가 범위에 있으면 -1;
        if (startTime <= MIDNIGHT && endTime >= MIDNIGHT) {
            // 중복 제거
            count--;
        }
        if (startTime <= NOON && endTime >= NOON) {
            // 중복 제거
            count--;
        }

        return count;
    }
}

        
// 시작과 끝 포함해서 알람 울림
// 3개 다 겹치면 알람 한번만 울림 (0시, 12시 말고는 없음)


        
        // 1초 마다 이동 O(60 *60 * 24), % 360
            // 
            // 60초 마다 flag 다시 hourFlag, minuteFlag false로 변경, 시침 % 60
            // 3600초 마다 분침 % 60
            // 43200초마다 시침 % 60
            // 시침이 분침 or 초침보다 커지면 count++
        