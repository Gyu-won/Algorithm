import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        // 문자열 S를 입력받는다
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        final String S = bf.readLine();

        // 26 크기의 배열을 생성 후 -1로 초기화한다
        List<Integer> alphabetLocation = new ArrayList<>(Collections.nCopies(26, -1));

        // 각 알파벳의 처음 나오는 위치로 update 한다.
        List<Character> word = new ArrayList<>();
        for (char character : S.toCharArray()) {
            word.add(character);
        }

        for (char alphabet : word) {
            if (word.contains(alphabet)) {
                alphabetLocation.set(alphabet - 'a', word.indexOf(alphabet));
            }
        }

        // 결과를 출력한다.
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int result : alphabetLocation) {
            bw.write(Integer.toString(result) + " ");
        }
        bw.flush();
        bf.close();
        bw.close();
    }
}