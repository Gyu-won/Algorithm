import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // numberOfCity를 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numberOfCity = Integer.parseInt(br.readLine());

        // distances를 입력받는다.
        int[] distances = new int[numberOfCity - 1];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < numberOfCity - 1; i++) {
            distances[i] = Integer.parseInt(st.nextToken());
        }

        // prices를 하나씩 minHeap에 넣는다.
        st = new StringTokenizer(br.readLine(), " ");
        PriorityQueue<Integer> priceMinHeap = new PriorityQueue<>();
        priceMinHeap.add(Integer.parseInt(st.nextToken()));

        long minPrice = 0;
        for (int distance : distances) {
            // minHeap에서 제일 위에 값을 가져와서 distance와 곱해서 더한다.
            minPrice += distance * priceMinHeap.peek();
            priceMinHeap.add(Integer.parseInt(st.nextToken()));
        }

        System.out.println(minPrice);
    }
}

// 가장 최소로 가려면 가장 싼곳에서 많이 넣어야 한다.
// 이떄까지 온 주유소 중에서 가장 싼 가격으로 다음 주요소까지 넣는다.

// 알고리즘: 현재 상황에서 가장 싼 가격을 넣는 것이므로 그리디 알고리즘을 사용한다.

// 시간복잡도: O(distance 길이)

// 정수 범위: 가격은 long으로