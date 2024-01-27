import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public int solution() throws IOException {
        // N을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // problems를 입력받는다.
        String problems = br.readLine();

        char start = problems.charAt(0);
        char flag;
        if (start == 'B') {
            flag = 'R';
        } else {
            flag = 'B';
        }

        int count = 1;
        char before = 'B';
        for (char problem : problems.toCharArray()) {
            if (problem == flag && before == start) {
                count++;
            }
            before = problem;
        }

        return count;
    }
}

// 알고리즘: 가장 적은 횟수로 칠하려면, 현재상황에서 달라져야하는 부분을 모두 같은색으로 칠하면 된다.

// 시간복잡도: O(N)

// 정수 범위
