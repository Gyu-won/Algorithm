import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        // 초기 배열을 지정한다.
        int[] numberOfAlphabet = new int[26];

        // S를 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (char alphabet : br.readLine().toCharArray()) {
            numberOfAlphabet[alphabet - 'a'] += 1;
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numberOfAlphabet.length; i++) {
            result.append(numberOfAlphabet[i] + " ");
        }
        System.out.println(result.toString().trim());
    }
}