import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        // N을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // N개의 알파벳을 입력받고 중복을 제거하여 저장한다.
        Set<String> uniqueWordSet = new HashSet<>();
        for (int i = 0; i < N; i++) {
            uniqueWordSet.add(br.readLine());
        }
        List<String> uniqueWords = new ArrayList<>(uniqueWordSet);

        // 정렬한다.
        uniqueWords.sort(new Comparator<String>() {
            @Override
            public int compare(String firstWord, String secondWord) {
                if (firstWord.length() > secondWord.length()) {
                    return 1;
                }
                if (firstWord.length() == secondWord.length()) {
                    return firstWord.compareTo(secondWord);
                }
                return -1;
            }
        });

        //출력한다.
        StringBuilder result = new StringBuilder();
        uniqueWords.forEach(uniqueWord -> result.append(uniqueWord + "\n"));
        System.out.println(result);
    }
}