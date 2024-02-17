import java.util.*;
import java.time.*;
import java.time.format.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        // LocalTime[n] bus를 구한다 (hh:mm)
        LocalTime[] buses = new LocalTime[n];
        buses[0] = LocalTime.parse("09:00");
        for (int i = 1; i < n; i++){
            buses[i] = buses[i-1].plusMinutes(t);
        }
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        
        // timetable을 정렬한다
        int size = timetable.length;
        Arrays.sort(timetable);
        
        LocalTime start = LocalTime.parse("00:00");
        LocalTime end = LocalTime.from(buses[n-1]);
        
        // 00:00 부터 bus[n-1] 구간 중 이분탐색
        while (!start.isAfter(end)){
            // mid 구하기
            int minutes1 = start.getHour() * 60 + start.getMinute();
            int minutes2 = end.getHour() * 60 + end.getMinute();

            int midMinutes = (minutes1 + minutes2) / 2;
            LocalTime mid = LocalTime.of(midMinutes / 60, midMinutes % 60);

            // bus를 순회한다.
            int idx = 0;
            
            boolean flag = false;
            
            flagLoop:
            for (LocalTime bus : buses){
                int count = 0;
                // bus 인원 다 안찼으면 채우기
                while (count < m){
                    // timetable이랑 mid 비교해서 mid가 더 작으면 mid가 버스보다 작으면 태우기 (start 옮기기)
                    if (idx < size){
                        LocalTime fTime = LocalTime.parse(timetable[idx]);
                        if (mid.isBefore(fTime)){
                            if (!mid.isAfter(bus)){
                                // count가 여유가 있고, mid가 timetable보다는 앞서며, bus에 탈 수 있을 때 탐
                                flag = true;
                                break flagLoop;    
                            }else{
                                // mid가 timetable보다 앞서긴 하지만 bus에 못탈 때
                                break;
                            }
                        }else{
                            // timetable이 앞서고 bus에 탈 때
                            if (!fTime.isAfter(bus)){
                                count++;
                                idx++;
                            }else{
                                // timetable이 앞서는데 bus에 못탈 떄                                
                                break;
                            }
                        }
                    }else{
                        // timetable은 다타고 mid 만 남은 경우
                        if (!mid.isAfter(bus)){
                                // count가 여유가 있고, mid가 timetable보다는 앞서며, bus에 탈 수 있을 때 탐
                            flag = true;
                            break flagLoop;    
                        }else{
                            // mid가 timetable보다 앞서긴 하지만 bus에 못탈 때
                            break;
                        }
                    }
                }
            }
            
            if (flag){
                start = mid.plusMinutes(1);
            }else{
                end = mid.minusMinutes(1);
            }
        }
        
        
        return end.format(formatter);
    }
}

// 요약
// 9시부터 n회, t분 간격으로, 한번당 m 명 탑승가능, 해당시간까지 줄 선 크루 탑승 가능
// 도착 시간 중 제일 늦은 시간, 같은 시간이면 제일 뒤에 선다, 모든 크루는 23:59
//n(1-10), t(1-60), m(1-45), timetable (1-2000)

// O(1440 * n * m)

// 11:13 - 11:22(-6)