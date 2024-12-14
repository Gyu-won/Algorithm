import java.util.*;

class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {

        Time videoLen = new Time(video_len);
        Time current = new Time(pos);
        Time opStart = new Time(op_start);
        Time opEnd = new Time(op_end);
        
        if (isOpening(opStart, opEnd, current)){
            current.moveToOpEnd(opEnd);
        }
        
        for (String command: commands) {
            if (command.equals("prev")){
                current.moveToPrev();
            }else{
                current.moveToNext(videoLen);
            }
            
            if (isOpening(opStart, opEnd, current)){
                current.moveToOpEnd(opEnd);
            }
        }
        
        return current.toString();
    }
    
    private static boolean isOpening(Time opStart, Time opEnd, Time current){
        return (opStart.totalSecond <= current.totalSecond) && (current.totalSecond <= opEnd.totalSecond);
    }
    
    private static class Time {
        private int totalSecond;
        
        private Time (String time){
            StringTokenizer st = new StringTokenizer(time, ":");
            int minute = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
                
            this.totalSecond = minute * 60 + second;
        }
        
        private void moveToPrev() {
            totalSecond -= 10;
            
            if (totalSecond < 0){
                totalSecond = 0;
            }
        }
        
        private void moveToNext(Time videoLen){
            totalSecond += 10;
            
            if (totalSecond > videoLen.totalSecond){
                totalSecond = videoLen.totalSecond;
            }
        }
            
        private void moveToOpEnd(Time opEnd){
            totalSecond = opEnd.totalSecond;
        }
        
        @Override
        public String toString() {
            int minute = totalSecond / 60;
            int second = totalSecond % 60;
            return String.format("%02d:%02d", minute, second);
        }
    }
}



// 10초 전으로 이동: prev -> 처음이면 0 or 아니면 10초 전으로 이동
// 10초 후로 이동: next -> 마지막 위치 or 10초 후로 이동
// 오프닝 건너뛰기: op_start <= opend 이면 오프닝 끝나는 위치로 이동
// 출력: 입력 모두 끝난후 위치를 mm:ss 형식으로 출력

// 오프닝 구간 이동
// command 명령어 실행 (next, prev)

// 시간 연산은 초부터 (덧셈, 뺄셈)