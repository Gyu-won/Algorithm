import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public String solution() throws IOException {
        // n을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 확장자와 count를 저장할 Map<String, Integer> files 를 선언한다.
        Map<String, Integer> files = new HashMap<>();
        for (int i = 0; i < n; i++) {
            // n번 입력받으며 정규표현식을 사용하여 map에 저장한다.
            String file = br.readLine();
            String extension = file.substring(file.indexOf('.') + 1);

            int count = files.getOrDefault(extension, 0);
            files.put(extension, count + 1);
        }

        // map을 확장자명으로 정렬한후 출력한다.
        StringBuilder result = new StringBuilder();
        List<String> sortedFiles = new ArrayList<>(files.keySet());
        Collections.sort(sortedFiles);
        for (String file : sortedFiles) {
            result.append(file);
            result.append(" ");
            result.append(files.get(file));
            result.append("\n");
        }

        return result.toString().trim();
    }
}

// 알고리즘: 정규표현식과 map을 활용하여 개수대로 묶고 오름차순 정렬하면 된다.

// 시간복잡도: O(n * logn)

// 정수 범위:
