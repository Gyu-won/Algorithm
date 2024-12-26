import java.util.*;

class Solution {
    public int solution(int coin, int[] cards) {
        
        int n = cards.length, life = 0;
        int[] sequence = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sequence[i] = -1;
        }
        
        for (int i = 0; i < n/3; i++) {
            int card = cards[i];
            sequence[card] = i;
            if (sequence[n + 1 -card] > -1) {
                life++;
            }
        }
        
        Deque<Integer> oneCoinQueue = new ArrayDeque<>();
        Deque<Integer> twoCoinQueue = new ArrayDeque<>();
        for (int i = n/3; i < n; i++) {
            int card = cards[i];
            sequence[card] = i;
             if (sequence[n + 1 -card] > -1) {
                if (sequence[n+1-card] < n/3) {
                    oneCoinQueue.addLast(i);
                }else{
                    twoCoinQueue.addLast(i);
                }
            }
        }
        
        return gameStart(n, coin, life, oneCoinQueue, twoCoinQueue); 
    }
    
    private static int gameStart(int n, int coin, int life, Deque<Integer> oneCoinQueue, Deque<Integer> twoCoinQueue) {
        
        int round = 1 + life;
        while (round <= n/3) {
            if (!oneCoinQueue.isEmpty() && (oneCoinQueue.getFirst() - n/3) / 2 < round) {
                if (coin >= 1) {
                    coin--;
                    round++;
                    oneCoinQueue.removeFirst();
                    continue;
                }
                break;
            }
            if (!twoCoinQueue.isEmpty() && (twoCoinQueue.getFirst() - n/3) / 2 < round) {
                if (coin >= 2){
                    coin -= 2;
                    round++;
                    twoCoinQueue.removeFirst();
                    continue;
                }
            }
            return round;
        }
        return round;
    }
}

// n/3까지 for문 돌면서도 sequence에 자기 순서 넣고, 
    // sequence[13-n]이 -1보다 크면 
        // life++
// for문 돌면서 sequence에 자기 순서 넣기
    // sequence[13-n]이 -1보다 크면 
        // n/3보다 작을 떄는 oneCoinQueue에 넣기
        // 크거나 같으면 twoCoinQueue에 넣기
// round 값 1로 세팅 후
// (oneCoinQueue.getFirst()- n/3) / 2 < round + life
    // coin >= 1이면 true면 coin--, round++, continue
    // < 1이면 break;
// 아니면  (twoCoinQueue.getFirst() - n/3) / 2 < round + life
    // coin >= 2이면, true 면  coint -=2, round++, continue
    // < 2면 break;
// break;
// return round + life;

// 13:14 - 14:35
// n+1을 만들고 못만들면 종료
// 결과: 게임에서 도달할 수 있는 최대 라운드 수
// 완전 탐색
// 카드 다 받는 경우, a만, b만 받는경우, 안받는 경우
// n/3 -> 최대 333라운드


