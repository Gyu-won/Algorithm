import java.util.*;

class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        
        while(!canIn(wallet, bill)) {
            fold(bill);
            answer++;
        }
         
        return answer;
    }
    
    private static void fold(int[] bill) {
        bill[1] /= 2;
    }
    
    private static boolean canIn(int[] wallet, int[] bill) {
        Arrays.sort(wallet);
        Arrays.sort(bill);
        
        return bill[0] <= wallet[0] && bill[1] <= wallet[1];
    }
}

// 결과: bill의 두 값이 모두 wallet의 두 값보다 작으려면 몇번 접어야 하는지

