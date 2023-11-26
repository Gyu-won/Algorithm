import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        // 단어를 입력받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();

        // 거꾸로 문자열을 생성한다
        StringBuilder reverseWord = new StringBuilder(word).reverse();

        // 둘이 같은지 비교 후 결과를 출력한다
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        if (word.contentEquals(reverseWord)) {
            bw.write(Integer.toString(1));
        } else {
            bw.write(Integer.toString(0));
        }

        bw.flush();
        br.close();
        bw.close();
    }
}