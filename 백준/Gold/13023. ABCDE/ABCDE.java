import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // List<Integer>[n] relation을 생성한다.
        List<Integer>[] relation = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            relation[i] = new ArrayList<>();
        }

        // m 을 입력받아 relation 을 연결한다.
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int p1 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());

            relation[p1].add(p2);
            relation[p2].add(p1);
        }

        // 시작점을 달리해서 dfs를 수행한다.
        for (int i = 0; i < n; i++) {
            if (flag) {
                break;
            }
            dfs(i, 1, new boolean[n], relation);
        }

        if (flag) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    private static void dfs(int current, int count, boolean[] visited, List<Integer>[] relation) {
        if (count == 5) {
            flag = true;
            return;
        }

        for (int friend : relation[current]) {
            if (!visited[friend]) {
                visited[current] = true;
                dfs(friend, count + 1, visited, relation);
                visited[current] = false;
            }
        }
    }
}

// n (5-2000), m (1-2000)

// O(n * n)

// 15:04