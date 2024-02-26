import java.util.*;

class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        
        // logs를 초로 변경해서 time 만들어줌
        List<Time> times = new ArrayList<>();
        for (int i = 0; i < logs.length; i++){
            StringTokenizer st = new StringTokenizer(logs[i], "-");
            
            times.add(new Time(st.nextToken(), true));
            times.add(new Time(st.nextToken(), false));
        }
        
        int playSeconds = convertToSecond(play_time);
        int advSeconds = convertToSecond(adv_time);
        
        // times 정렬 O(nlogn)
        Collections.sort(times, new TimeComparator());
        
        // 시작이면 count+1, 끝이면 -1 하면서 watching 채우기 O(t)
        int count = 0, current = 0;
        int[] watching = new int[playSeconds];
        for (int i = 0; i < times.size(); i++){
            Time time = times.get(i);
            for (int j = current; j < time.time; j++){
                watching[j] = count;
            }
            
            current = time.time;
            if (time.isStart){
                count++;
            }else{
                count--;
            }
        }
        
        // for (int i = 1500; i < 3000; i++){
        //     System.out.println(i + ": " + watching[i]);
        // }
        
        // 투포인터로 window 크기만큼 값 구해서 더 크면 maxValue랑 시작시간 update
        int startSeconds = twoPointer(advSeconds, playSeconds, watching);
        // System.out.println(startSeconds);
        
        // 시작시간 반환
        return convertToTime(startSeconds);   
    }
    
    private static String convertToTime(int seconds){
        StringBuilder time = new StringBuilder();
        
        int h = seconds / 3600;
        if (h < 10){
            time.append(0);
        }
        time.append(h);
        time.append(":");
        
        int m = (seconds % 3600) / 60;
        if (m < 10){
            time.append(0);
        }
        time.append(m);
        time.append(":");
        
        int s = (seconds % 60);
        if (s < 10){
            time.append(0);
        }
        time.append(s);
        
        return time.toString();
    }
    
    private static int twoPointer(int windowSize, int n, int[] watching){
        int start = 0, end = 0;
        long sum = 0;
        for (; end < windowSize; end++){
            sum += watching[end];
        }
        
        long maxValue = sum;
        int startTime = 0;
        for (; end < n; end++){
            sum += watching[end];
            sum -= watching[start++];
            
            if (maxValue < sum){
                maxValue = sum;
                startTime = start;
            }
        }
        
        return startTime;
    }
    
    
    private static int convertToSecond(String time){
        StringTokenizer st = new StringTokenizer(time, ":");
        int hour = Integer.parseInt(st.nextToken()) * 3600;
        int minute = Integer.parseInt(st.nextToken()) * 60;
        int second = Integer.parseInt(st.nextToken());
        
        return hour + minute + second;
    }
    
    private static class TimeComparator implements Comparator<Time> {
        @Override
        public int compare(Time t1, Time t2){
            return t1.time - t2.time;
        }
    }
    
    private static class Time {
        private final int time;
        private final boolean isStart;
        
        Time (String time, boolean isStart){
            this.time = convertToSecond(time);
            this.isStart = isStart;
        }
    }
}

// 누적 재생시간을 구해서 최대인곳을 출력, 시작위치를 리턴, 여러개면 가장 빠른 곳
// logs: 1-300,000 이하 문자열 배열, 각 길이는 17 -로 구분

// 09:45 - 

// logs를 초로 변경해서 정렬
// 윈도우가 정해져 있기 때문에 투포인터 사용

// O(nlogn) 까지 가능