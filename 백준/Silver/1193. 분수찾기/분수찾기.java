

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        // X를 입력받는다
        int X = Integer.parseInt(bf.readLine());

        // X의 분모가 어디에 해당하는지 구한다.
        int startX = 1;
        int sum = 1;
        for (int i = 1; i < 10000000; i++) {
            if (X < startX + i) {
                sum += i;
                break;
            } else {
                startX += i;
            }
        }

        int diff = X - startX;
        int denominator, numerator;
        if (sum % 2 == 0) {
            numerator = sum - 1 - diff;
            denominator = 1 + diff;
        } else {
            denominator = sum - 1 - diff;
            numerator = 1 + diff;
        }
        
        // 결과 출력
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(numerator) + "/" + String.valueOf(denominator));
        bw.flush();
        bf.close();
        bw.close();
    }
}