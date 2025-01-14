class Solution {
    public int solution(int coin, int[] cards) {

        int n = cards.length;
        int target = n + 1;
        
        // cardRound[] = -1로 기본 설정
        int[] cardRound = new int[n+1];
        for (int i = 1; i <= n; i++) {
            cardRound[i] = -1;
        }
        
        // n/3까지 cardRound[] = 0 으로 설정
        for (int i = 0; i < n/3; i++) {
            cardRound[cards[i]] = 0;
        }

        // 최대 n/3라운드 반복
        int round = 1;
        boolean roundSurvive;
        for (; round <= n/3; round++) {
            roundSurvive = false;
            
            // 뽑은 카드는 cardRound[] = round 에 저장
            int selectIdx = n/3 + 2 * (round -1);
            cardRound[cards[selectIdx]] = round;
            cardRound[cards[selectIdx + 1]] = round;
            
            // coin 0개로 가능한지 확인: n/3 순회하면 cardRound[target - i] = 0 이면 -1로 바꾸고 통과 O(n/3)
            for (int i = 0; i < n/3; i++) {
                if (cardRound[target - cards[i]] == 0) {
                    cardRound[cards[i]] = -1;
                    cardRound[target - cards[i]] = -1;
                    roundSurvive = true;
                    break;
                }
            }
            
            if (roundSurvive) {
                continue;
            }
            
            // coin 1개로 가능한지 확인: coin > 0 일때
            if (coin < 1) {
                break;
            }
            
            // n/3 순회하며 cardRound[target - i] > 0 이면 -1로 바꾸고, coin -= 1, 통과 O(n/3)
            for (int i = 0; i < n/3; i++) {
                if (cardRound[target - cards[i]] > 0) {
                    cardRound[cards[i]] = -1;
                    cardRound[target - cards[i]] = -1;
                    roundSurvive = true;
                    coin -= 1;
                    break;
                }
            }
            
            if (roundSurvive) {
                continue;
            }
            
            // coin 2개로 가능한지 확인:
            if (coin < 2) {
                break;
            }
            
            // n/3 부터 현재 위치까지 순회하며 cardRound[target -i] > 0 이면 -1로 바꾸고 coin -= 2 통과 O(2*n/3)    
            for (int i = n/3; i < selectIdx + 2; i++) {
                if (cardRound[target - cards[i]] > 0) {
                    cardRound[cards[i]] = -1;
                    cardRound[target - cards[i]] = -1;
                    roundSurvive = true;
                    coin -= 2;
                    break;
                }
            }
            
            if (!roundSurvive) {
                break;
            }
        }
        
        return round;
    }
}

// 카드 n/3개 가짐, 동전 coin 개
// 라운드 마다 카드 2개씩 뽑음, 동전 하나써서 가지거나 버리거나
// 카드 적힌 값이 n+1이 되도록 내면 다음 라운드 
// n+1을 못내거나 카드 못 뽑으면 게임 종료


// O(n^2)