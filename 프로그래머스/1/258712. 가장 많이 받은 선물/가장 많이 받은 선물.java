import java.util.*;

class Solution {
    private static int n;
    private static int[] giftQuantity;
    private static int[][] giftBoard;
    
    public int solution(String[] friends, String[] gifts) {
        
        n = friends.length;
        Map<String, Integer> friendMap = createFriendMap(friends);
        
        fillGiftHistory(gifts, friendMap);
        return calculateMaxGivePoint();
    }
    
    private static int calculateMaxGivePoint() {
        int maxGivePoint = 0;
        for(int i = 0; i < n; i++) {
            int givePoint = 0;
            for(int j = 0; j < n; j++) {
                if(i == j) {
                    continue;
                }
                if(canGetGift(i, j)) {
                    givePoint++;
                }
            }
            maxGivePoint = Math.max(maxGivePoint, givePoint);
        }
        return maxGivePoint;
    }
    
    private static boolean canGetGift(int i, int j) {
        return giftBoard[i][j] > giftBoard[j][i] || (giftBoard[i][j] == giftBoard[j][i] && giftQuantity[i] > giftQuantity[j]);
    }
    
    private static void fillGiftHistory(String[] gifts, Map<String, Integer> friendMap) {
        StringTokenizer st;
        giftQuantity = new int[n];
        giftBoard = new int[n][n];
        for (int i = 0; i < gifts.length; i++) {
            st = new StringTokenizer(gifts[i]);
            int giver = friendMap.get(st.nextToken());
            int taker = friendMap.get(st.nextToken());

            giftQuantity[giver]++;
            giftQuantity[taker]--;
            giftBoard[giver][taker]++;
        }
    }
    
    private static Map<String, Integer> createFriendMap(String[] friends) {
        Map<String, Integer> friendMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            friendMap.put(friends[i], i);
        }
        return friendMap;
    }
}

// friends로 map 만들기
// giftBoard[][] 만들기
// giftQuantity[] 만들기
// 이중 for문 돌면서 각 학생마다 선물 개수 구하기
    // [i][j] 랑 [j][i] 비교, 선물 지수 비교
    // max 값 update


// 선물 더 많이 준 사람, (준 선물 수 - 받은 선물 수) / 같으면 선물 안주고 받음
// 결과: 다음달에 가장 많은 선물을 받는 친구가 받을 선물의 수