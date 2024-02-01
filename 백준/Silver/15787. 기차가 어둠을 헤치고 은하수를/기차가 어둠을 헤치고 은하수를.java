import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    private static final int FIRST_SEAT_NUMBER = 1;
    private static final int LAST_SEAT_NUMBER = 20;

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public int solution() throws IOException {
        // numberOfTrain과 nubmerOfCommand를 입력받는다. (int)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int numberOfTrain = Integer.parseInt(st.nextToken());
        int numberOfCommand = Integer.parseInt(st.nextToken());

        // int[numberOfTrain+1][22] trains를 선언한다.
        int[][] trains = new int[numberOfTrain + 1][22];

        // 명령을 입력받으며 명령을 수행한다.
        for (int i = 0; i < numberOfCommand; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            String command = st.nextToken();
            int trainNumber = Integer.parseInt(st.nextToken());
            if (command.equals("1")) {
                int seatNumber = Integer.parseInt(st.nextToken());
                trains[trainNumber][seatNumber] = 1;
            } else if (command.equals("2")) {
                int seatNumber = Integer.parseInt(st.nextToken());
                trains[trainNumber][seatNumber] = 0;
            } else if (command.equals("3")) {
                for (int j = LAST_SEAT_NUMBER; j >= FIRST_SEAT_NUMBER; j--) {
                    trains[trainNumber][j] = trains[trainNumber][j - 1];
                }
            } else {
                for (int j = FIRST_SEAT_NUMBER; j <= LAST_SEAT_NUMBER; j++) {
                    trains[trainNumber][j] = trains[trainNumber][j + 1];
                }
            }
        }

        // trains를 문자열로 바꾸어서 Set<String> galaxyTrain에 넣는다.
        Set<String> galaxyTrain = new HashSet<>();
        for (int i = 1; i <= numberOfTrain; i++) {
            StringBuilder train = new StringBuilder();
            for (int j = FIRST_SEAT_NUMBER; j <= LAST_SEAT_NUMBER; j++) {
                train.append(trains[i][j]);
            }
            galaxyTrain.add(train.toString());
        }

        // galaxyTrian의 크기를 반환한다.
        return galaxyTrain.size();
    }
}

// 알고리즘: 모든 명령을 다 수행하고, 다시 앞에서부터 건널 수 있는 기차를 count 해야한다.
// 명령 수행은 단순 구현하면 되고, 기차 count를 할 때 set을 활용하면 된다.

// 시간복잡도:

// 정수 범위:
