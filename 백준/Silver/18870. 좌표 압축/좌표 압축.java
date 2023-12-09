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
        // 그냥 비교하면 O(n제곱)으로 시간 초과가 된다.
        // 크기를 비교하는 것이기 때문에 정렬 후 처음 등장하는 index를 얻어오면 된다.

        //N을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // N개의 좌표를 입력받아서 저장한다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Integer> coordinates = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            coordinates.add(Integer.parseInt(st.nextToken()));
        }

        List<Integer> sortedCoordinates = new ArrayList<>(coordinates);

        // 좌표들을 정렬한다.
        Collections.sort(sortedCoordinates);

        int rank = 0;
        Map<Integer, Integer> rankMap = new HashMap<>();
        for (int coordinate : sortedCoordinates) {
            if (!rankMap.containsKey(coordinate)) {
                rankMap.put(coordinate, rank);
                rank++;
            }
        }

        // 각 숫자의 index를 얻어와서 출력 형식을 만든다.
        StringBuilder result = new StringBuilder();
        coordinates.forEach(coordinate -> result.append(rankMap.get(coordinate) + " "));

        System.out.println(result);
        br.close();
    }
}