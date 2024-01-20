import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

class Solution {

    private static int len;
    private static Map<String, Boolean> visited = new HashMap<>();
    private static Deque<String> wordQueue;

    public int solution(String begin, String target, String[] words) {

        len = begin.length();

        // 방문 기록을 위한 visited 생성
        for (String word : words) {
            visited.put(word, Boolean.FALSE);
        }

        // bfs를 사용하여 최적 경로를 찾음
        return bfs(begin, target, words);
    }

    private int bfs(String begin, String target, String[] words) {
        Map<String, Integer> depths = new HashMap<>();

        wordQueue = new ArrayDeque<>();
        wordQueue.add(begin);
        depths.put(begin, 0);

        while (!wordQueue.isEmpty()) {
            String currentWord = wordQueue.removeFirst();

            if (currentWord.equals(target)) {
                return depths.get(target);
            }

            for (String word : words) {
                int sameCount = 0;
                for (int i = 0; i < len; i++) {
                    if (currentWord.charAt(i) == word.charAt(i)) {
                        sameCount++;
                    }
                }
                if (sameCount == len - 1 && !visited.get(word)) {
                    depths.put(word, depths.get(currentWord) + 1);
                    visited.put(word, Boolean.TRUE);
                    wordQueue.add(word);
                }
            }
        }
        return 0;
    }
}

// begin에서 target으로 가는 최적경로 (bfs)
// 각 단어에서 어느 단어로 갈 수 있는지를 저장