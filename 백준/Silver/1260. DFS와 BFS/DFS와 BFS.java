import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    private static boolean[] dfsVisited;
    private static boolean[] bfsVisited;
    private static ArrayList<Integer>[] graph;
    private static int dfsCount = 1;
    private static int bfsCount = 1;

    public static void main(String[] args) throws IOException {
        // numberOfNode, numberOfEdge, start 를 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int numberOfNode = Integer.parseInt(st.nextToken());
        int numberOfEdge = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());

        // 방문 여부 배열을 생성한다.
        dfsVisited = new boolean[numberOfNode + 1];
        bfsVisited = new boolean[numberOfNode + 1];

        // 간선을 입력받으면서 간선을 등록한다.
        graph = new ArrayList[numberOfNode + 1];
        for (int i = 1; i <= numberOfNode; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < numberOfEdge; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int firstNode = Integer.parseInt(st.nextToken());
            int secondNode = Integer.parseInt(st.nextToken());
            graph[firstNode].add(secondNode);
            graph[secondNode].add(firstNode);
        }

        // 각각의 연결된 간선들을 오름차순 정렬한다.
        for (int i = 1; i <= numberOfNode; i++) {
            Collections.sort(graph[i]);
        }

        // dfs 를 수행한다, count 포함
        System.out.println(dfs(start, String.valueOf(start)));

        // bfs를 수행한다.
        System.out.println(bfs(start, String.valueOf(start)));
    }

    private static String bfs(int start, String result) {
        Deque<Integer> nodes = new ArrayDeque<>();
        nodes.add(start);
        bfsVisited[start] = true;

        while (!nodes.isEmpty()) {
            for (int node : graph[nodes.poll()]) {
                if (!bfsVisited[node]) {
                    bfsVisited[node] = true;
                    result += " " + node;
                    nodes.offer(node);
                }
            }
        }
        return result;
    }

    private static String dfs(int start, String result) {
        dfsVisited[start] = true;
        if (dfsCount == dfsVisited.length - 1) {
            return result;
        }

        for (int node : graph[start]) {
            if (!dfsVisited[node]) {
                dfsCount++;
                result = dfs(node, result + " " + node);
            }
        }
        return result;
    }
}