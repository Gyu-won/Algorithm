import java.util.Stack;

class Solution {
    private final Stack<Character> stack = new Stack<>();

        public int solution(String s) {
            int answer = 0;
            StringBuilder stringBuilder = new StringBuilder(s);

            for (int i = 0; i < s.length(); i++) {
                stringBuilder.append(stringBuilder.charAt(0));
                stringBuilder.deleteCharAt(0);
                if (correctParenthesis(stringBuilder.toString().toCharArray()))
                    answer++;
            }
            return answer;
        }

        private boolean correctParenthesis(char[] s) {
            for (char c : s) {
                if (!(check(c, '(', ')') && check(c, '[', ']') && check(c, '{', '}')))
                    return false;
            }
            return stack.isEmpty();
        }

        private boolean check(char c, char a, char b) {
            if (c == a)
                stack.push(a);
            else if (c == b)
                if (!stack.isEmpty() && stack.peek() == a) stack.pop(); else return false;
            return true;
        }
}