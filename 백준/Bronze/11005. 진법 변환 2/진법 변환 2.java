
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        // N과 B를 입력 받는다.
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        //10진법 수 N을 B진법으로 변환한다
        List<Integer> nNumbers = new ArrayList<>();

        int n = N;
        for (; n >= B; n = n / B) {
            nNumbers.add(n % B);
        }
        nNumbers.add(n);

        //문자열로 변환한다.
        StringBuilder nNumber = new StringBuilder();
        for (int index = nNumbers.size() - 1; index >= 0; index--) {
            int number = nNumbers.get(index);
            if (number < 10) {
                nNumber.append((char) (number + '0'));
            } else {
                nNumber.append((char) (number - 10 + 'A'));
            }
        }

        // 결과 출력
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(nNumber.toString());
        bw.flush();
        bf.close();
        bw.close();
    }
}