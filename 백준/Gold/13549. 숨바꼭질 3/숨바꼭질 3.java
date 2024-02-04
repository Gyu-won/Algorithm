import java.util.*;
 
public class Main {    
 
    static int min = Integer.MAX_VALUE;
    static int n, k;
    static boolean[] visited;
    static int max = 100000;
    
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        
        n = scan.nextInt();
        k = scan.nextInt();
        
        visited = new boolean[max + 1];
        bfs();
        System.out.println(min);
    }
    
    public static void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(n, 0));
        
        while(!q.isEmpty()) {
            Node node = q.poll();
            visited[node.x] = true;
            if(node.x == k) min = Math.min(min, node.time);
            
            if(node.x * 2 <= max && visited[node.x * 2] == false) q.offer(new Node(node.x * 2, node.time));
            if(node.x + 1 <= max && visited[node.x + 1] == false) q.offer(new Node(node.x + 1, node.time + 1));
            if(node.x - 1 >= 0 && visited[node.x - 1] == false) q.offer(new Node(node.x - 1, node.time + 1));
        }
    }
    
    public static class Node {
        int x;
        int time;
        
        public Node(int x, int time) {
            this.x = x;
            this.time = time;
        }
    }
}

// 19:53 - 21:14
// 총 걸린 시간: 01:21

// 알고리즘: 특별한 방법 없이 해당 값이 나올 때까지 계속 탐색해야한다.
// 최소 횟수를 알아야 하기 때문에 bfs를 사용하면 된다.

// 시간복잡도: ??

// 정수 범위:
