import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        // N을 입력받는다.
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());

        // 해당하는 숫자가 몇개 등장했는지 개수를 저장하는 배열을 만든다.
        int[] count = new int[10001];

        // 정렬할 숫자를 입력받는다.
        for (int i = 0; i < N; i++) {
            count[Integer.parseInt(bf.readLine())]++;
        }

        // 결과 출력
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 1; i < 10001; i++) {
            for (int j = 0; j < count[i]; j++) {
                bw.write(i + "\n");
            }

        }
        bw.flush();
        bf.close();
        bw.close();
    }
}