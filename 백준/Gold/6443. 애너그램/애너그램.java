import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    private static int n;
    private static int[] alphabets = new int[26];

    public static void main(String[] args) throws IOException {
        // n 을 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        Set<String> words = new HashSet<>();

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            // alphabets 입력
            char[] inputs = br.readLine().toCharArray();
            for (int j = 0; j < inputs.length; j++) {
                alphabets[inputs[j] - 'a']++;
            }

            // dfs
            dfs(inputs.length, "", words);

            List<String> uniqueWords = new ArrayList<>(words);

            // set 정렬
            Collections.sort(uniqueWords);

            // 출력
            for (String word : uniqueWords) {
                result.append(word);
                result.append("\n");
            }

            words.clear();
            for (int j = 0; j < 26; j++) {
                alphabets[j] = 0;
            }
        }
        System.out.println(result.toString().trim());

    }

    private static void dfs(int m, String word, Set<String> words) {
        if (word.length() == m) {
            words.add(word);
            return;
        }

        for (int i = 0; i < alphabets.length; i++) {
            if (alphabets[i] > 0) {
                alphabets[i]--;
                dfs(m, word + (char) ('a' + i), words);
                alphabets[i]++;
            }
        }
    }
}

// 11:57
