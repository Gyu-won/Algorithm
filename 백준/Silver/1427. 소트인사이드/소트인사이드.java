import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        // N을 입력받는다.
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String N = bf.readLine();

        // 자리수를 나누어 list로 저장한다.
        List<Integer> sortedArray = new ArrayList<>();
        for (Character digit : N.toCharArray()) {
            sortedArray.add(Character.getNumericValue(digit));
        }

        //정렬
        Collections.sort(sortedArray);

        //역순으로 출력
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int size = sortedArray.size();
        for (int i = 0; i < size; i++) {
            bw.write(Integer.toString(sortedArray.get(size - 1 - i)));
        }

        bw.flush();
        bf.close();
        bw.close();
    }
}