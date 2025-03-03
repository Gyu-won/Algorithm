import java.util.*;

class Solution {
    
    // onboard 길이 <= 1,000
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        boolean[][] visited = new boolean[onboard.length][51];
        
        int tempRate = -1;
        if (temperature > t2) {
            tempRate = 1;
        }
        
        PriorityQueue<Indoor> pq = new PriorityQueue<>(new IndoorComparator());
        pq.add(new Indoor(temperature, 0, 0));
    
        int minEnergy = 0;
        while(!pq.isEmpty()) {
            Indoor indoor = pq.poll();
            
            if (visited[indoor.time][indoor.temperature + 10]) {
                continue;
            }
            
            visited[indoor.time][indoor.temperature + 10] = true;
            if (indoor.time == onboard.length - 1) {
                minEnergy = indoor.energy;
                break;
            }
            
            if (onboard[indoor.time + 1] == 0) {
                if (indoor.temperature == temperature) {
                    pq.add(new Indoor(indoor.temperature, indoor.time + 1, indoor.energy));   
                } else {
                    pq.add(new Indoor(indoor.temperature + tempRate, indoor.time + 1, indoor.energy));   
                    pq.add(new Indoor(indoor.temperature, indoor.time + 1, indoor.energy + b));
                }
                if (!isOutBound(indoor.temperature - tempRate, -10, 40)) {
                    pq.add(new Indoor(indoor.temperature - tempRate, indoor.time + 1, indoor.energy + a));
                }            
            } else {
                if (!isOutBound(indoor.temperature + tempRate, t1, t2)) {
                    pq.add(new Indoor(indoor.temperature + tempRate, indoor.time + 1, indoor.energy));
                }
                if (!isOutBound(indoor.temperature, t1, t2)) {
                    pq.add(new Indoor(indoor.temperature, indoor.time + 1, indoor.energy + b));    
                }
                if (!isOutBound(indoor.temperature - tempRate, t1, t2)) {
                    pq.add(new Indoor(indoor.temperature - tempRate, indoor.time + 1, indoor.energy + a));
                } 
            }
        }        
        
        return minEnergy;
    }
    
    private boolean isOutBound(int temp, int t1, int t2) {
        return temp < t1 || temp > t2;
    }
    
    private class Indoor {
        private final int temperature;
        private final int time;
        private final int energy;
        
        Indoor (int temperature, int time, int energy) {
            this.temperature = temperature;
            this.time = time;
            this.energy = energy;
        }
    }
    
    private class IndoorComparator implements Comparator<Indoor> {
        @Override
        public int compare(Indoor i1, Indoor i2) {
            if (i1.energy == i2.energy) {
                return i2.time - i1.time;
            }
            return i1.energy - i2.energy;
        }
    }
}

// q에 넣기 전에 다음 온도의 여부 확인 후 넣기, 
// 전력, 시간 같은지도 확인