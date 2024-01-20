import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // jewelNumber, bagNumber를 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int jewelNumber = Integer.parseInt(st.nextToken());
        int bagNumber = Integer.parseInt(st.nextToken());

        // jewel weight, jewel price를 입력받아 list로 저장한다.
        List<Jewel> jewels = new ArrayList<>();
        for (int i = 0; i < jewelNumber; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            jewels.add(new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        // 보석을 무게순으로 오름차순 정렬한다.
        Collections.sort(jewels, new JewelComparator());

        // 가방을 입력받는다.
        int[] bagsWeight = new int[bagNumber];
        for (int i = 0; i < bagNumber; i++) {
            bagsWeight[i] = Integer.parseInt(br.readLine());
        }

        // 가방을 무게로 오름차순 정렬한다.
        Arrays.sort(bagsWeight);

        // 가방을 하나씩 채운다.
        long answer = 0;
        int bagIndex = 0;
        PriorityQueue<Integer> priceMaxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (Jewel jewel : jewels) {
            // 넣어야 하는 보석의 무게가 가방 최대 무게보다 크면 heap에서 가격을 들고와서 더해준다.
            while (bagIndex < bagNumber && jewel.weight > bagsWeight[bagIndex]) {
                if (priceMaxHeap.peek() != null) {
                    answer += (long) priceMaxHeap.poll();
                }
                bagIndex++;
            }
            if (bagIndex == bagNumber) {
                break;
            }
            priceMaxHeap.add(jewel.price);
        }

        for (int i = bagIndex; i < bagNumber; i++) {
            if (priceMaxHeap.peek() == null) {
                break;
            } else {
                answer += (long) priceMaxHeap.poll();
            }
        }

        System.out.println(answer);
    }

    private static class Jewel {
        private final int weight;
        private final int price;

        public Jewel(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }
    }

    private static class JewelComparator implements Comparator<Jewel> {
        @Override
        public int compare(Jewel j1, Jewel j2) {
            return j1.weight - j2.weight;
        }
    }
}

// 보석 개수, 가방 개수
// 보석의 무게, 보석 가격
// 가방에 담을 수 있는 최대 무게

// 가방 최대 무게가 가벼운 것 부터
// 가방에 담을 수 있는 것 중에서 제일 비싼걸 담으면 된다. (정렬)

