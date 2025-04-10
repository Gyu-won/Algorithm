class Solution {
    public long solution(int w, int h) {
        // 최대 공약수 구하기
        int gcdValue = 0;
        if (w >= h) {
            gcdValue = gcd(w, h);
        } else {
            gcdValue = gcd(h, w);
        }
        
        // 최대 공약수로 나눈 값으로 계산
        return (long) w * (long) h - countNonSquare(w / gcdValue, h / gcdValue) * (long) gcdValue;
    }
    
    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        
        return gcd(b, a % b);
    }
    
    private long countNonSquare(int r, int c) {
        return ((long) r) + ((long) c) - 1;
    }
}

// 전체 개수 - 겹치는 개수
// 겹치는 개수: 매번 다 세면 O(1억) -> 최대공약수로 해서 최적화