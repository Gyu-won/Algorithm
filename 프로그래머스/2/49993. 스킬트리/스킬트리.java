import java.util.*;

class Solution {
    
    private static Map<Character, Character> sequence = new HashMap<>();
    
    public int solution(String skill, String[] skill_trees) {    
        // 이전꺼를 저장 (B: C, D:B)
        for (int i = 1; i < skill.length(); i++) {
            sequence.put(skill.charAt(i), skill.charAt(i-1));
        }
        
        // skill_trees 순회
        int count = 0;
        for (String skillTree: skill_trees) {
            if (isValidTree(skillTree)) {
                count++;
            }
        }
        return count;
    }
    
    private boolean isValidTree(String skillTree) {
        Set<Character> mastered = new HashSet<>();
        for (char ch: skillTree.toCharArray()) {
            if (sequence.containsKey(ch) && !mastered.contains(sequence.get(ch))) {
                return false;
            }
            mastered.add(ch);
        }
        return true;
    }
}
