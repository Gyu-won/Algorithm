import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    private static boolean[] visited;
    private static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        // numberOfComputer를 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numberOfComputer = Integer.parseInt(br.readLine());

        // 방문 여부 배열을 선언한다.
        visited = new boolean[numberOfComputer + 1];

        // numberOfNetwork를 입력받는다.
        int numberOfNetwork = Integer.parseInt(br.readLine());

        // 에지를 입력받으면서 인접리스트에 저장한다.
        graph = new ArrayList[numberOfComputer + 1];
        for (int i = 1; i < numberOfComputer + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < numberOfNetwork; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int firstNode = Integer.parseInt(st.nextToken());
            int secondNode = Integer.parseInt(st.nextToken());
            graph[firstNode].add(secondNode);
            graph[secondNode].add(firstNode);
        }

        // 방문 노드 개수를 구한다.
        System.out.println(dfs(1, 0));
    }

    private static int dfs(int start, int count) {
        visited[start] = true;
        for (int end : graph[start]) {
            if (!visited[end]) {
                count = dfs(end, count + 1);
            }
        }
        return count;
    }
}

// 그래프에서 뭉치의 개수를 구하는 것이기 때문에 dfs
// 1번컴퓨터에서 dfs 해서 방문했던것의 개수를 확인
