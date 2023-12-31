import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        // S를 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();

        // 접미사 List를 생성한다.
        List<String> suffixes = new ArrayList<>();
        for (int i = 0; i < S.length(); i++) {
            suffixes.add(S.substring(i));
        }

        // 접미사 List를 사전순으로 정렬한다.
        Collections.sort(suffixes);

        // 결과를 출력한다.
        StringBuilder result = new StringBuilder();
        suffixes.forEach(suffix -> result.append(suffix + "\n"));
        System.out.println(result);
    }
}