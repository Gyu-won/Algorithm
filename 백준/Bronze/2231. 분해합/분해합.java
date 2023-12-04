import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // N을 입력받는다.
        int N = Integer.parseInt(bf.readLine());
        int M = 0;

        for (int mCandidate = 1; mCandidate < N; mCandidate++) {
            int splitSum = mCandidate;
            String mCandidateOfString = String.valueOf(mCandidate);
            for (int index = 0; index < mCandidateOfString.length(); index++) {
                splitSum += mCandidateOfString.charAt(index) - '0';
            }

            if (splitSum == N) {
                M = mCandidate;
                break;
            }
        }

        // 결과 출력
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(M));
        bw.flush();
        bf.close();
        bw.close();
    }
}