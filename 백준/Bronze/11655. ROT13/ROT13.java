import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        // S를 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();

        // ROT13 암화화를 수행한다.
        StringBuilder result = new StringBuilder();
        for (char character : S.toCharArray()) {
            char encryptedCharacter;
            if (character >= 'A' && character <= 'Z') {
                encryptedCharacter = (char) ('A' + (character - 'A' + 13) % 26);
            } else if (character >= 'a' && character <= 'z') {
                encryptedCharacter = (char) ('a' + (character - 'a' + 13) % 26);
            } else {
                encryptedCharacter = character;
            }
            result.append(encryptedCharacter);
        }

        // 결과를 출력한다.
        System.out.println(result);
    }
}