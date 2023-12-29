import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {
    public int solution(String str1, String str2) {
        // 집합 쌍을 계산 (대소문자 구분 x, 영문자만 유효)
        Map<String, Integer> set1 = createSet(str1);
        Map<String, Integer> set2 = createSet(str2);

        Set<String> union = new HashSet<>();
        union.addAll(set1.keySet());
        union.addAll(set2.keySet());

        if (union.isEmpty()) {
            return 65536;
        }

        List<String> intersection = new ArrayList<>(set1.keySet());
        intersection.retainAll(set2.keySet());

        double intersectionNumber = 0.0;
        int unionNumber = 0;
        for (String key : union) {
            if (intersection.contains(key)) {
                int set1Number = set1.get(key);
                int set2Number = set2.get(key);
                intersectionNumber += Integer.min(set1Number, set2Number);
                unionNumber += Integer.max(set1Number, set2Number);
                continue;
            }
            unionNumber += set1.getOrDefault(key, 0);
            unionNumber += set2.getOrDefault(key, 0);
        }

        return (int) Math.floor(intersectionNumber / unionNumber * 65536);
    }

    private Map<String, Integer> createSet(String str) {
        Map<String, Integer> set = new HashMap<>();
        char[] chars = str.toUpperCase().replaceAll("[^A-Z]", " ").toCharArray();

        for (int i = 0; i < chars.length - 1; i++) {
            String element = String.valueOf(chars[i]) + String.valueOf(chars[i + 1]);
            if (element.trim().length() == 2) {
                set.put(element, set.getOrDefault(element, 0) + 1);
            }
        }
        return set;
    }
}