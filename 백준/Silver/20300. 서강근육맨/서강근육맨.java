import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public long solution() throws IOException {
        // numberOfMachine을 입력받는다. int
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numberOfMachine = Integer.parseInt(br.readLine());

        // muscleLosses 를 입력받는다. long[]
        long[] muscleLosses = new long[numberOfMachine];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < numberOfMachine; i++) {
            muscleLosses[i] = Long.parseLong(st.nextToken());
        }

        // mucleLosses를 오름차순 정렬한다.
        Arrays.sort(muscleLosses);

        // numberOfMachine이 홀수라면 가장 근손실이 큰 운동을 제외한다.
        long maxDayLoss = 0;
        if (numberOfMachine % 2 == 1) {
            numberOfMachine--;
            maxDayLoss = muscleLosses[numberOfMachine];
        }

        // numberOfMachine이 2개씩 양 끝값을 하나씩 더하면서 최소값을 구한다.
        for (int i = 0; i < numberOfMachine / 2; i++) {
            long muscleLoss = muscleLosses[i] + muscleLosses[numberOfMachine - i - 1];
            maxDayLoss = Math.max(muscleLoss, maxDayLoss);
        }

        // 결과를 출력한다.
        return maxDayLoss;
    }
}

// 알고리즘: 운동 개수를 짝수개로 만들고 현재 가능한 운동의 근손실 최소와 최대를 더함.

// 시간복잡도: O(logN + N)

// 정수 범위
