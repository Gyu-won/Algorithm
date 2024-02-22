import java.util.*;

class Solution {
    
    private static final int[] discountRate = new int[] {10, 20, 30, 40};
    
    public int[] solution(int[][] users, int[] emoticons) {
        int n = users.length;
        int m = emoticons.length;
        
        // 각각의 할인율 지정하기 O(4^m) int[m] discounts
        List<int[]> discounts = new ArrayList<>();
        combination(0, m, new int[m], discounts);
        
        // for (int i = 0; i < discounts.size(); i++){
        //     for (int j = 0; j < m; j++){
        //         System.out.print(discounts.get(i)[j]);
        //     }
        //     System.out.println();
        // }
        
        int maxCount = 0;
        int maxPrice = 0;
        
        for (int[] discount: discounts){
            // 구매하기 Purchase 리턴 O(n)
            int[] purchaseInfo = purchase(n, m, discount, emoticons, users);
            
            // System.out.println(purchaseInfo[0]);
            // System.out.println(purchaseInfo[1]);
            // System.out.println("--------");
        
            // maxPurchase update
            if (maxCount < purchaseInfo[0]){
                maxCount = purchaseInfo[0];
                maxPrice = purchaseInfo[1];
            }else if (maxCount == purchaseInfo[0] && maxPrice < purchaseInfo[1]){
                maxPrice = purchaseInfo[1];
            }
        
        }
            
        return new int[]{maxCount, maxPrice};
    }
    
    private static int[] purchase(int n, int m, int[] discount, int[] emoticons, int[][] users){
        
        int plusCount = 0;
        int totalPrice = 0;
        for (int i = 0; i < n; i++){
            int userRate = users[i][0];
            int userPrice = users[i][1];
            
            int price = 0;
            for (int j = 0; j < m; j++){
                int rate = discountRate[discount[j]];
                if (userRate <= rate){
                    // 구매
                    price += emoticons[j] * (100 - rate) / 100;
                }
            }
            
            if (userPrice <= price){
                plusCount++;
            }else{
                totalPrice += price;
            }
        }
        
        return new int[]{plusCount, totalPrice};
    }
    
    private static void combination(int count, int m, int[] current, List<int[]> discounts){
        if (count == m){
            int[] temp = new int[m];
            for (int i = 0; i < m; i++){
                temp[i] = current[i];
            }
            discounts.add(temp);
            return;
        }
        for (Integer i = 0; i < discountRate.length; i++){
            current[count] = i;
            combination(count+1, m, current, discounts);
        }
    }
}

// 요약
// n명의 사용자에게 m개의 이모티콘 할인 판매 (1-100) (1-7) (10, 20, 30, 40)
// n개의 비율, 가격 (1-40), (100 - 1000000) (100배수)
// m개의 이모티콘 (100-1000000)가격, 100의 배수
// 최대 이모티콘 플러스, 최대 판매가격
// 플러스 가입자 수, 매출액 정수 배열 리턴

// O(4^m * n * m)