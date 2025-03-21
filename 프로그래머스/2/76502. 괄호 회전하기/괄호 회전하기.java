import java.util.*;

class Solution {
    
    private int len;
    private Deque<Character> stringQueue = new ArrayDeque<>();

    public int solution(String s) {
        len = s.length();
        for (char ch: s.toCharArray()) {
            stringQueue.add(ch);
        }
        
        int count = 0;
        for (int i = 0; i < len; i++) {
            rotate();
            if (isValid(new ArrayDeque<>(stringQueue))) {
                count++;
            }
        }
        return count;
    }
    
    private boolean isValid(Deque<Character> queue) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            char ch = queue.removeFirst();
            queue.addLast(ch);
            
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
        stringQueue.addLast(stringQueue.removeFirst());
    }
}