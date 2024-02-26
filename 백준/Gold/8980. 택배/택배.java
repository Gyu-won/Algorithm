import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        // n 과 c를 입력 (2-2000) (1-10000)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        // m입력 (1-10000)
        int m = Integer.parseInt(br.readLine());

        // boxes 입력받기
        Box[] boxes = new Box[m];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int amount = Integer.parseInt(st.nextToken());

            boxes[i] = new Box(start, dest, amount);
        }

        // boxes를 도착지 오름차순, 출발지 내림차순으로 정렬
        Arrays.sort(boxes, new BoxComparator());

        // truck 배열 생성
        int[] truck = new int[n + 1];

        // boxes 하나씩 순회
        int totalLoad = 0;
        for (int i = 0; i < m; i++) {
            Box box = boxes[i];

            // 출발지부터 도착지 -1 까지 truck 최대값 구하기
            int maxQuantity = 0;
            for (int j = box.start; j < box.dest; j++) {
                maxQuantity = Math.max(truck[j], maxQuantity);
            }

            // c - truck 이랑 box.amount 중 작은 것을 구하기
            int loadAmount = Math.min(c - maxQuantity, box.amount);

            // 전체에 더하기
            totalLoad += loadAmount;

            // 작은거를 출발지 - 도착지 -1 까지 더해주기
            for (int j = box.start; j < box.dest; j++) {
                truck[j] += loadAmount;
            }
        }

        System.out.println(totalLoad);
    }

    private static class BoxComparator implements Comparator<Box> {
        @Override
        public int compare(Box b1, Box b2) {
            if (b1.dest == b2.dest) {
                return b2.start - b1.start;
            }
            return b1.dest - b2.dest;
        }
    }

    private static class Box {
        private final int start;
        private final int dest;
        private final int amount;

        Box(int start, int dest, int amount) {
            this.start = start;
            this.dest = dest;
            this.amount = amount;
        }
    }
}

//

// O(n * m) 가능

// 11:22 -

