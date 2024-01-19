import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        // N을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 카드 묶음 수를 입력받아서 최소힙에 넣는다.
        PriorityQueue<Integer> decks = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            decks.add(Integer.parseInt(br.readLine()));
        }

        // 힙의 size가 1이라면 종료
        int count = 0;
        while (decks.size() > 1) {
            // 힙에서 2개를 꺼내서 더하고 다시 힙에 넣는다.
            int newDeck = decks.poll() + decks.poll();
            count += newDeck;
            decks.add(newDeck);
        }

        System.out.println(count);
    }
}
