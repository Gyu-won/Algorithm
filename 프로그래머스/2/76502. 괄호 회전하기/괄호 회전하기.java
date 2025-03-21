import java.util.*;

class Solution {

    private int len;
    private StringBuilder string;

    public int solution(String s) {
        len = s.length();
        string = new StringBuilder(s);

        int count = 0;
        for (int i = 0; i < len; i++) {
            rotate();
            if (isValid(string.toString())) {
                count++;
            }
        }
        return count;
    }

    private boolean isValid(String str) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            char ch = str.charAt(i);

            if (ch == ']') {
                if (stack.isEmpty() || stack.getLast() != '[') {
                    return false;
                }
                stack.removeLast();
            } else if (ch == '}') {
                if (stack.isEmpty() || stack.getLast() != '{') {
                    return false;
                }
                stack.removeLast();
            }else if (ch == ')') {
                if (stack.isEmpty() || stack.getLast() != '(') {
                    return false;
                }
                stack.removeLast();
            } else {
                stack.addLast(ch);    
            }
        }    
        return stack.isEmpty();
    }

    private void rotate() {
        string.append(string.charAt(0));
        string.deleteCharAt(0);
    }
}