import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        // N과 문자열을 입력받는다
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        final int N = Integer.parseInt(bf.readLine());
        String number = bf.readLine();

        // 합을 계산한다
        int sum = 0;
        char[] numbers = number.toCharArray();
        for (int index = 0; index < N; index++) {
            sum += Character.getNumericValue(numbers[index]);
        }

        //결과를 출력한다.
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Integer.toString(sum) + "\n");

        bw.flush();
        bf.close();
        bw.close();
    }
}