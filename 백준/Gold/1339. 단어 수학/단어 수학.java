import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static Map<Character, Integer> charMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        // N을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<String> words = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            // 모든 단어를 입력받는다.
            words.add(br.readLine());

            // 자리수를 보면서 각 문자에 자리수 값을 저장한다.
            char[] characters = words.get(i).toCharArray();
            int len = characters.length - 1;
            for (int j = 0; j <= len; j++) {
                charMap.put(characters[j],
                        charMap.getOrDefault(characters[j], 0) + (int) Math.pow(10, len - j));
            }
        }

        // 문자를 내림차순 정렬한다.
        List<Character> keys = new ArrayList<>(charMap.keySet());
        Collections.sort(keys, new CharacterComparator());

        // 정렬한 문자에 9부터 값을 할당하면서 값을 계산한다.
        int answer = 0;
        for (int i = 0; i < keys.size(); i++) {
            answer += charMap.get(keys.get(i)) * (9 - i);
        }
        System.out.println(answer);
    }

    private static class CharacterComparator implements Comparator<Character> {
        @Override
        public int compare(Character c1, Character c2) {
            return charMap.get(c2) - charMap.get(c1);
        }
    }
}
