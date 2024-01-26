import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    public void solution() throws IOException {
        // cityNumber, roadNumber, targetDistance, startCity를 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int cityNumber = Integer.parseInt(st.nextToken());
        int roadNumber = Integer.parseInt(st.nextToken());
        int targetDistance = Integer.parseInt(st.nextToken());
        int startCity = Integer.parseInt(st.nextToken());

        // minDistances를 선언후 초기화 한다.
        int[] minDistances = new int[cityNumber + 1];
        Arrays.fill(minDistances, Integer.MAX_VALUE);
        minDistances[startCity] = 0;

        // roadNumber 만큼 입력받아 인접리스트를 구성한다.
        ArrayList<Integer>[] graph = new ArrayList[cityNumber + 1];
        for (int i = 1; i <= cityNumber; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < roadNumber; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph[from].add(to);
        }

        // Queue<Integer> cityQueue를 생성하여 시작점을 담는다.
        Deque<Integer> cityQueue = new ArrayDeque<>();
        cityQueue.offer(startCity);

        // 다익스트라 알고리즘을 활용해 minDistance를 update 한다.
        while (!cityQueue.isEmpty()) {
            int from = cityQueue.poll();
            for (int to : graph[from]) {
                int distance = minDistances[from] + 1;
                if (distance < minDistances[to]) {
                    minDistances[to] = distance;
                    cityQueue.add(to);
                }
            }
        }

        // minDistances와 distance가 같은것을 하나씩 출력한다. 없으면 -1;
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < minDistances.length; i++) {
            if (minDistances[i] == targetDistance) {
                result.append(i);
                result.append("\n");
            }
        }

        if (result.length() == 0) {
            System.out.println(-1);
        } else {
            System.out.println(result.toString().trim());
        }
    }
}

// 알고리즘: 특정 노드에서 다른 모든 노드까지 가는 거리를 구해야 하기 떄문에 다익스트라 알고리즘을 사용한다.

// 시간복잡도: O(M)

// 정수 범위
