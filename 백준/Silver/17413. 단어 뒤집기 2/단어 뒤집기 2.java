import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        //문자열을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();

        // 정규식 패턴을 명시한다.
        Pattern pattern = Pattern.compile("<[a-z ]{1,}>|[a-z0-9 ]+");
        Matcher matcher = pattern.matcher(S);

        // 태그와 단어를 분리한다.
        List<String> parts = new ArrayList<>();
        while (matcher.find()) {
            parts.add(matcher.group());
        }

        // 분리된 부분이 단어이면 뒤집어서 결과에 추가한다.
        StringBuilder result = new StringBuilder();
        for (String part : parts) {
            if (part.startsWith("<") && part.endsWith(">")) {
                result.append(part);
            } else {
                StringTokenizer st = new StringTokenizer(part, " ");
                reverseWord(st, result);
            }
        }

        System.out.println(result);
    }

    private static void reverseWord(StringTokenizer st, StringBuilder result) {
        StringBuilder reverseWord = new StringBuilder(st.nextToken());
        result.append(reverseWord.reverse());
        if (st.hasMoreTokens()) {
            result.append(" ");
            reverseWord(st, result);
        }
    }
}