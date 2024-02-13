import java.util.*;

class Solution {
    public String solution(String p) {
        return getAnswer(p);
    }
    
    private String getAnswer(String w){
        //1
        if (w.length() == 0){
            return "";
        }  
        
        //2
        int left = 0;
        int right = 0;
        for (char c : w.toCharArray()){
            if (c == ')'){
                right++;
            }else{
                left++;
            }
            if (left == right){
                break;
            }
        }
        
        String u = w.substring(0,right+left);
        String v = w.substring(right+left);
        
        //3
        if (isValid(u)){
            return u + getAnswer(v);
        }else{
            StringBuilder reverseStr =  new StringBuilder();
            for (char c: u.substring(1,u.length()-1).toCharArray()){
                if (c == ')'){
                    reverseStr.append('(');
                }else{
                    reverseStr.append(')');
                }
            }
            return "(" + getAnswer(v) + ")" + reverseStr;
        }
    }
    
    private boolean isValid(String u){
        int lCount = 0;
        int rCount = 0;
        for (char c: u.toCharArray()){
            if (c == ')'){
                rCount++;
            }else{
                lCount++;
            }
            if (lCount < rCount){
                return false;
            }
        }
        return true;
    }
}

// 요약
// 괄호 개수는 맞는데 짝이 안맞음
// 

// 20:06 - 

// O()