import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // A, B, C, D를 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        // AB 와 CD를 구한다.
        long AB = 0;
        long CD = 0;
        while (st.hasMoreTokens()) {
            AB = Long.parseLong(st.nextToken() + st.nextToken());
            CD = Long.parseLong(st.nextToken() + st.nextToken());
        }

        System.out.println(AB + CD);
    }
}