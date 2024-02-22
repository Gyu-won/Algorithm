import java.util.*;

class Solution {
    
    private static int idx;
    
    public int[][] solution(int[][] nodeinfo) {
        
        int n = nodeinfo.length;
        
        // nodes 만들기
        Node[] nodes = createNodes(n, nodeinfo);
        // for (int i = 0; i < n; i++){
        //     System.out.println(nodes[i].number + " " + nodes[i].x + " " + nodes[i].y);
        // }
        
        // nodes 를 y로 정렬
        Arrays.sort(nodes, new NodeComparator());
        // for (int i = 0; i < n; i++){
        //     System.out.println(nodes[i].number + " " + nodes[i].x + " " + nodes[i].y);
        // }
        
        // root node 선언
        Node root = nodes[0];
        
        // 이진 트리를 만들기 O(nlogn)
        createBinaryTree(n, root, nodes);
            
        // 전위순회 O(n)
        idx = 0;
        int[] preOrders = new int[n];
        preOrder(preOrders, root);
        
        // 후위순회 O(n)
        idx = 0;
        int[] postOrders = new int[n];
        postOrder(postOrders, root);
        
        int[][] result = new int[2][n];
        result[0] = preOrders;
        result[1] = postOrders;
        return result;
    }
    
    private static void postOrder(int[] result, Node node){      
        if (node != null){
            postOrder(result, node.left);            
            postOrder(result, node.right);
            result[idx++] = node.number;            
        }
    }
    
    
    private static void preOrder(int[] result, Node node){        
        if (node != null){
            result[idx++] = node.number;
            preOrder(result, node.left);            
            preOrder(result, node.right);               
        }
    }
    
    private static Node[] createNodes(int n, int[][] nodeinfo){
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++){
            nodes[i] = new Node(i+1, nodeinfo[i][0], nodeinfo[i][1]);
        }
        return nodes;
    }
    
    private static void createBinaryTree(int n, Node root, Node[] nodes){
        for (int i = 1; i < n; i++){
            insert(nodes[i], root);
        }
    }
    
    private static void insert(Node node, Node current){
        if (current.x < node.x){
            if (current.right == null){
                current.right = node;
            }else{
                insert(node, current.right);
            }
        }else{
            if (current.left == null){
                current.left = node;                
            }else{
                insert(node, current.left);
            }
        }
    }
    
    static class Node {
        private final int number;
        private final int x;
        private final int y;
        
        private Node left;
        private Node right;
        
        private Node(int number, int x, int y){
            this.number = number;
            this.x = x;
            this.y = y;
        }
    }
    
    static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare (Node n1, Node n2){
            return n2.y - n1.y;
        }
    }
}

// 제한
// 트리의 x, y는 정수, 모든 서로 다른 x 값
// y는 레벨을 뜻함, 자식 노드의 y는 부모 노드의 y보다 작음

// 1번부터 번호 시작 (1-10,000)
// 모든 좌표는 (0-100,000) 정수
// 트리 깊이는 1000 이하


// O(nlogn)

// 17:16