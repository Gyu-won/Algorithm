import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int n;
    private static int[] alphabets = new int[26];
    private static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws IOException {
        // n 을 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            // alphabets 입력
            char[] inputs = br.readLine().toCharArray();
            for (int j = 0; j < inputs.length; j++) {
                alphabets[inputs[j] - 'a']++;
            }

            // dfs
            dfs(inputs.length, "");

            for (int j = 0; j < 26; j++) {
                alphabets[j] = 0;
            }
        }
        System.out.println(result.toString().trim());

    }

    private static void dfs(int m, String word) {
        if (word.length() == m) {
            result.append(word);
            result.append("\n");
            return;
        }

        for (int i = 0; i < alphabets.length; i++) {
            if (alphabets[i] > 0) {
                alphabets[i]--;
                dfs(m, word + (char) ('a' + i));
                alphabets[i]++;
            }
        }
    }
}

// 11:57
