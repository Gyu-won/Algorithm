

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        // N과 B를 입력 받는다.
        StringTokenizer st = new StringTokenizer(bf.readLine());
        String N = st.nextToken();
        int B = Integer.parseInt(st.nextToken());

        // B진법수 N을 계산한다.
        int numberLength = N.length();
        char[] NNumbers = N.toCharArray();

        long totalNumber = 0;
        for (int index = 0; index < numberLength; index++) {
            int number = NNumbers[index];
            if (number > '9') {
                number = number - 'A' + 10;
            } else {
                number -= '0';
            }
            totalNumber += (long) (number * Math.pow(B, numberLength - 1 - index));
        }

        // 결과 출력
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(totalNumber));
        bw.flush();
        bf.close();
        bw.close();
    }
}