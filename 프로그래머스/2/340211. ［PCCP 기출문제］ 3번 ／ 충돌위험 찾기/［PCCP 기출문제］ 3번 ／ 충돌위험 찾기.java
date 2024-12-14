import java.util.*;

class Solution {
    private static int[][] map = new int[101][101];
    
    public int solution(int[][] points, int[][] routes) {
        int numberOfCollision = 0;
        int numberOfRobot = routes.length;
        
        int numberOfComplete = 0;
        boolean[] isComplete = new boolean[numberOfRobot];
        List<Robot> robotList = new LinkedList<>();
        
        for (int i = 0; i < numberOfRobot; i++) {
            int pointIdx = routes[i][0] - 1;
            int robotR = points[pointIdx][0];
            int robotC = points[pointIdx][1];
            
            map[robotR][robotC]++;
            robotList.add(new Robot(robotR, robotC, 1, routes[i]));
        }
        numberOfCollision += countCollision();
        
        while(numberOfComplete < numberOfRobot) {
            for (int i = 0; i < robotList.size(); i++) {
                if (!isComplete[i]) {
                    Robot robot = robotList.get(i);
                
                    if (robot.isDone()) {
                        map[robot.r][robot.c]--;
                        isComplete[i] = true;
                        numberOfComplete++;
                        continue;
                    }

                    int[] destination = robot.getDestination(points);
                    robot.move(destination);
                    robot.setDestination(destination);
                }
            }
            numberOfCollision += countCollision();
        }
        
        return numberOfCollision;
    }
    
    private static int countCollision() {
        int totalCollision = 0;
        for (int r = 1; r <= 100; r++) {
            for (int c = 1; c <= 100; c++) {
                if (map[r][c] > 1) {
                    totalCollision++;
                }
            }
        }
        return totalCollision;
    }
    
    private static class Robot {
        private int r;
        private int c;
        private int currentRoute;
        private int[] destinations;
        
        Robot (int r, int c, int currentRoute, int[] destinations) {
            this.r = r;
            this.c = c;
            this.currentRoute = currentRoute;
            this.destinations = destinations;
        }
        
        private boolean isDone() {
            return currentRoute == destinations.length;
        }
        
        private int[] getDestination(int[][] points) {
            int pointIdx = destinations[currentRoute] - 1;
            return points[pointIdx];
        }
        
        private void move(int[] destination) {
            int destR = destination[0];
            int destC = destination[1];
            
            map[this.r][this.c]--;
            if (this.r < destR) {
                this.r++;
            } else if(this.r > destR) {
                this.r--;
            } else if(this.c < destC) {
                this.c++;
            } else if (this.c > destC) {
                this.c--;
            }
            map[this.r][this.c]++;
        }
        
        private void setDestination(int[] destination) {
            if (destination[0] == r && destination[1] == c) {
                currentRoute++;
            }
        }
    }
}

// 로봇 배치
// 충돌 확인
// 이동: 각 로봇마다 목적지로 이동  (x, y, currentRoute, destinations[])
    // 종료 여부 확인(currentRoute), 종료 시 이전 visited 없에고 remove
    // 목적지 조회 (destination)
    // 이전 visited -1 하고 visited +1, x, y update O(x)
    // 목적지 재설정 (도착 시 다음 목적지로 설정)
// 충돌 확인 O(r * c)

// r, c 좌표로 나타내는 n개 포인트
// 운송경로는 m개의 포인트로 구성
// 로봇 총 x개 0초 출발하여 상하좌우 이동, 최단경로로 세로로 먼저 이동
// 현재 설정대로 할 때 몇 번 충돌, 2번 이상 모이면 충돌

// 결과: 이동이 끝날 때 까지 충돌 횟수를 리턴