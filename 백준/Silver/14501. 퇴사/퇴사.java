import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public int solution() throws IOException {
        // N을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // price 배열을 생성한다. int[]
        int[] price = new int[N];

        // timetable을 입력받는다. String[]
        String[] timetable = new String[N];
        for (int i = 0; i < N; i++) {
            timetable[i] = br.readLine();
        }

        // timetable의 뒤에서 부터 보며 해당 시간에 상담을 시작했을 때 최대 값을 저장한다.
        StringTokenizer st;
        for (int i = N - 1; i >= 0; i--) {
            st = new StringTokenizer(timetable[i]);
            int time = Integer.parseInt(st.nextToken());
            int salary = Integer.parseInt(st.nextToken());

            if (i + time < N) {
                int maxValue = 0;
                for (int j = i + time; j < N; j++) {
                    maxValue = Math.max(price[j], maxValue);
                }
                price[i] = maxValue + salary;
            } else if (i + time == N) {
                price[i] = salary;
            } else {
                price[i] = 0;
            }
        }

        // price 배열 중 최대 값을 출력한다.
        int maxSalary = 0;
        for (int i = 0; i < N; i++) {
            maxSalary = Math.max(price[i], maxSalary);
        }

        return maxSalary;
    }
}

// 알고리즘: N이 15이기 때문에 O(N!)도 가능하다. 뒤에서부터 해당 날짜에 가능한 최대 금액을 저장한다.

// 시간복잡도: O(N*N)

// 정수 범위