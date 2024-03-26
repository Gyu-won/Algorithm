import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        // n 입력 (1-50)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // parents 입력
        int[] parent = new int[n];

        // root 기억
        int root = -1;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            parent[i] = Integer.parseInt(st.nextToken());
            if (parent[i] == -1) {
                root = i;
            }
        }

        // 지울 노드 번호 입력
        int eraseNumber = Integer.parseInt(br.readLine());

        int leafCount = dfs(n, root, 0, parent) - dfs(n, eraseNumber, 0, parent);
        if (parent[eraseNumber] != -1 && isLeaf(n, eraseNumber, parent[eraseNumber], parent)) {
            leafCount++;
        }
        System.out.println(leafCount);
    }

    private static boolean isLeaf(int n, int erase, int parent, int[] parents) {
        for (int i = 0; i < n; i++) {
            if (parents[i] == parent && erase != i) {
                return false;
            }
        }
        return true;
    }

    private static int dfs(int n, int current, int leafCount, int[] parent) {
        boolean isLeaf = true;
        for (int i = 0; i < n; i++) {
            if (parent[i] == current) {
                leafCount = dfs(n, i, leafCount, parent);
                isLeaf = false;
            }
        }
        if (isLeaf) {
            leafCount++;
        }
        return leafCount;

        // parent 순회하면서 자기를 가리키는 애면서 지우는 번호가 아닌지 확인

        // empty면 leaf노드++
    }
}

// 트리 만들기 (O(n)) 왼쪽자식, 오른쪽 자신, 자기자신 번호
// 루트부터 dfs 하는데 왼, 오 가 -1이 아니거나 지우기로 한 번호 아니면 들어감
// 왼, 오 가 모두 -1 이거나 지우기로 한 노드면 count++, return;
