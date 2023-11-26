import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        // 단어를 입력받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();

        // 크로아티아 특수문자를 저장한다
        List<String> specificWords = List.of("c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z=");

        // 특수문자가 있으면 이를 제외한다
        int count = 0;
        int specificWordLength = 0;
        for (String specificWord : specificWords) {
            while (true) {
                int index = word.indexOf(specificWord);
                if (index != -1) {
                    count++;
                    word = word.substring(0, index) + " ".repeat(specificWord.length()) + word.substring(
                            index + specificWord.length());
                    specificWordLength += specificWord.length();
                } else {
                    break;
                }
            }
        }

        // 총 문자 개수를 출력한다
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Integer.toString(count + word.length() - specificWordLength));

        bw.flush();
        br.close();
        bw.close();
    }
}