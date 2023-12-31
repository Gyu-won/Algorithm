import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int[] solution(String msg) {
        List<Integer> answers = new ArrayList<>();

        Deque<Character> characterQueue = new ArrayDeque<>();
        for (Character character : msg.toCharArray()) {
            characterQueue.addLast(character);
        }

        Map<String, Integer> dictionary = new HashMap<>();
        int index = 1;
        for (int i = 65; i < 91; i++) {
            dictionary.put(String.valueOf((char) i), index++);
        }

        while (!characterQueue.isEmpty()) {
            String word = characterQueue.removeFirst().toString();
            while (!characterQueue.isEmpty() && dictionary.containsKey(word + characterQueue.getFirst())) {
                word += characterQueue.removeFirst();
            }
            answers.add(dictionary.get(word));
            if (!characterQueue.isEmpty()) {
                dictionary.put(word + characterQueue.getFirst(), index++);
            }
        }

        return answers.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}