import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int overNumber = calculateOverNumber(words);
        if (overNumber == -1) {
            return new int[] {0, 0};
        }
        return new int[] {overNumber % n + 1, overNumber / n + 1};
    }
    
    private int calculateOverNumber(String[] words) {
        if (words[0].length() == 1) {
            return 0;
        }
        
        Set<String> wordSet = new HashSet<>();
        wordSet.add(words[0]);
        String prev = words[0];
        
        
        for (int i = 1; i < words.length; i++) {
            String word = words[i];
            char lastChar = prev.charAt(prev.length() - 1);
            if (word.length() == 1 || wordSet.contains(word) || lastChar != word.charAt(0)) {
                 return i;
            }
            prev = word;
            wordSet.add(word);
        }
        return -1;
    }
}
