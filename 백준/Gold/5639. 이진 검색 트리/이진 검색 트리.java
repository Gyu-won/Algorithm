import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static Node root = null;
    private static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws IOException {
        // 전위 순회 결과 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while ((input = br.readLine()) != null) {
            int nodeNumber = Integer.parseInt(input);

            // 트리 만들기
            makeTree(root, new Node(nodeNumber));
        }

        // 후위순회
        postOrder(root);
        System.out.println(result.toString().trim());
    }

    private static void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        result.append(node.number);
        result.append("\n");
    }

    private static void makeTree(Node parent, Node node) {
        if (parent == null) {
            root = node;
            return;
        }

        if (parent.number < node.number) {
            if (parent.right == null) {
                parent.right = node;
                return;
            }
            makeTree(parent.right, node);
        } else {
            if (parent.left == null) {
                parent.left = node;
                return;
            }
            makeTree(parent.left, node);
        }
    }

    private static class Node {
        private final int number;
        private Node left;
        private Node right;

        Node(int number) {
            this.number = number;
        }
    }
}

// 10:10 -

// 노드 추기 O(nlogn), 순회 O(n)