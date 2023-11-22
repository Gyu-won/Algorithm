import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    private static final int NUMBER_OF_TOTAL_STUDENT = 30;
    private static final int NUMBER_OF_SUBMIT_STUDENT = 28;

    public static void main(String[] args) throws IOException {
        // 단어와, 숫자를 입력 받는다
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        final String word = bf.readLine();
        final int i = Integer.parseInt(bf.readLine());

        // 결과 출력
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(word.toCharArray()[i - 1]);
        bw.flush();
        bf.close();
        bw.close();
    }
}