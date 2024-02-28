import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        // S 와 T를 입력받는다 (1-999) (2-1000)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        StringBuilder T = new StringBuilder(br.readLine());

        int tLen = T.length();
        while (S.length() < tLen) {
            if (T.charAt(tLen - 1) == 'A') {
                T = new StringBuilder(T.substring(0, tLen - 1));
            } else {
                T = new StringBuilder(T.substring(0, tLen - 1));
                T.reverse();
            }
            tLen--;
        }

        if (S.contentEquals(T)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
}

// A를 추가하거나
// 뒤집고 B를 추가

// 2의 999승

// O(n * nlogn) 까지 가능