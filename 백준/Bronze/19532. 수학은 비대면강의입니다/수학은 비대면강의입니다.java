import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        // a, b, c, d, e, f 를 입력받는다.
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int f = Integer.parseInt(st.nextToken());

        // (c*e -f*b) / (a*e -d*b)는 x이다
        int x = (c * e - f * b) / (a * e - d * b);

        // (c - ax) / b 는 y이다.
        int y;
        if (b != 0) {
            y = (c - a * x) / b;
        } else {
            y = (f - d * x) / e;
        }

        // 결과 출력
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(x) + " " + String.valueOf(y));
        bw.flush();
        bf.close();
        bw.close();
    }
}