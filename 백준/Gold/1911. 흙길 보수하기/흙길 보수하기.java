import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // n 과 l을 입력 ( 1-10000), (1-1,000,000)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        // 웅덩이 입력
        List<WaterHole> waterHoles = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            waterHoles.add(new WaterHole(start, end));
        }

        // 웅덩이 정렬
        Collections.sort(waterHoles, new WaterHoleComparator());

        // 웅덩이 채우기
        System.out.println(fill(n, l, waterHoles));
    }

    private static int fill(int n, int l, List<WaterHole> holes) {
        int current = 0, count = 0;

        for (int idx = 0; idx < holes.size(); idx++) {
            // 웅덩이 시작점과 현재 위치 중 큰것부터 덧대기
            WaterHole hole = holes.get(idx);
            current = Math.max(current, hole.start);

            // 끝점보다 작으면 클때까지 덧대기
            while (current < hole.end) {
                current += l;
                count++;
            }
            // 끝점보다 크면 다음 웅덩이 확인
        }

        return count;
    }

    private static class WaterHoleComparator implements Comparator<WaterHole> {
        @Override
        public int compare(WaterHole h1, WaterHole h2) {
            return h1.start - h2.start;
        }
    }

    private static class WaterHole {
        private final int start;
        private final int end;

        WaterHole(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}

// 물웅덩이의 위치와 크기정보, 필요한 최소 널빤지 개수

// 그, 이, 디

// 웅덩이를 덮을때 최대한 시작점에 맞춰서 덮으면 됨
// 웅덩이를 시작점으로 정렬

// O(n)까지 가능
