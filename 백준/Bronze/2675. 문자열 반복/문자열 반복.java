import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // T를 입력받는다
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        final int T = Integer.parseInt(bf.readLine());

        // R과 S를 입력받는다
        StringTokenizer st;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int R;
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(bf.readLine());
            R = Integer.parseInt(st.nextToken());
            char[] words = st.nextToken().toCharArray();

            // 새로운 문자열 P를 만든다.
            StringBuilder P = new StringBuilder();
            for (char word : words) {
                P.append(String.valueOf(word).repeat(R));
            }

            // P를 출력한다.
            bw.write(P.toString() + "\n");
        }
        bw.flush();
        bf.close();
        bw.close();
    }
}