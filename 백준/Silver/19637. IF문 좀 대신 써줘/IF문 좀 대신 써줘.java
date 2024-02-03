import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public String solution() throws IOException {
        // n, m을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // Map<Integer, String> nicknames를 입력받는다.
        Map<Integer, String> nickNames = new HashMap<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            String nickName = st.nextToken();
            int power = Integer.parseInt(st.nextToken());
            if (!nickNames.containsKey(power)) {
                nickNames.put(power, nickName);
            }
        }

        // m번 power를 입력받으며 powers에서 삽입되어야 하는 위치를 찾고 nickname을 출력한다.
        StringBuilder result = new StringBuilder();
        List<Integer> powers = new ArrayList<>(nickNames.keySet());
        Collections.sort(powers);
        for (int i = 0; i < m; i++) {
            int power = Integer.parseInt(br.readLine());
            int index = Collections.binarySearch(powers, power);
            if (index < 0) {
                index = (index + 1) * -1;
            }
            result.append(nickNames.get(powers.get(index)));
            result.append("\n");
        }

        // 결과를 출력한다.
        return result.toString().trim();
    }
}

// 15:45 -
// 총 걸린 시간:

// 알고리즘: 칭호의 개수가 최대 10^5개 이기 떄문에 if 문이 아니라 이분탐색을 이용한다.

// 시간복잡도: O(m * logn)

// 정수 범위: int
