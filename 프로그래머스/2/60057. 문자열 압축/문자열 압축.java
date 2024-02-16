import java.util.*;

class Solution {
    public int solution(String s) {

        int length = s.length();
        
        int minLength = Integer.MAX_VALUE;
        
        if (length == 1){
            return 1;
        }
        
        // l: length / 2 만큼 반복
        for (int l = 1; l <= length/2; l++){
            // before 선언
            String before = s.substring(0, l);
            
            // count 는 1
            int count = 1, current = l;
            
            StringBuilder result = new StringBuilder();
            // current l부터 length까지 l 씩 더해주면서
            for (; current + l <= length; current += l){
                // substring하고 같으면 count++
                String temp = s.substring(current, current + l);
                if (before.equals(temp)){
                    count++;
                }else{
                    // 다르면 1제외 count 랑 before 를 결과에 추가, count와 before 초기화    
                    if (count > 1){
                        result.append(count);
                    }            
                    result.append(before);
                    count = 1;
                    before = temp;
                }    
            }
            if (count > 1){
                result.append(count);
            }            
            result.append(before);
            
            minLength = Math.min(minLength, result.length() + length - current);
        }
        
        return minLength;
    }
}

// 요약
// 같은 문자 연속이면 반복 횟수 문자로 표현 (1은 생략)
// 근데 문자를 몇개 단위로 자를지 고민
// 자르고 남는거는 그대로 붙여주면 됨
// 가장 짧은 길이의 압축 문자의 길이를 리턴 (s: 1-1000, 알파벳 소문자)

// s의 길이 / 2 까지만 비교해서 제일 최소를 알면 됨

// O(k * (len ))

// 22:37 - 