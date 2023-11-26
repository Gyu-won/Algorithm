import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        // 단어를 입력받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine().toUpperCase();

        // 자주 등장하는 단어를 찾는다
        Map<Character, Integer> charCount = new HashMap<>();
        char mostFrequentChar = ' ';
        int mostFrequentCount = 0;

        for (char character : word.toCharArray()) {
            charCount.put(character, charCount.getOrDefault(character, 0) + 1);
            if (charCount.get(character) == mostFrequentCount) {
                mostFrequentChar = '?';
            }
            if (charCount.get(character) > mostFrequentCount) {
                mostFrequentCount = charCount.get(character);
                mostFrequentChar = character;
            }
        }

        // 결과를 출력한다.
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(mostFrequentChar);

        bw.flush();
        br.close();
        bw.close();
    }
}