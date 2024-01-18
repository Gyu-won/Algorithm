import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // N을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // S와 T를 입력받는다.
        List<Lecture> lectures = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            lectures.add(new Lecture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        // 입력받은 후 시작시간 순, 종료되는 순으로 정렬
        Collections.sort(lectures, new LectureComparator());

        // 추가할 강의의 시작시간이 가장 빨리 끝나는 강의보다 같거나, 늦게 시작하면 가장 빨리 시작하는 강의 뺴고 추가 강의 종료시간으로 변경
        // 추가할 강의가 가장 빨리 끝나는 강의보다 빨리 시작하면 그냥 더하기만
        PriorityQueue<Integer> rooms = new PriorityQueue<>();
        rooms.add(lectures.get(0).end);
        for (int i = 1; i < lectures.size(); i++) {
            if (lectures.get(i).start >= rooms.peek()) {
                rooms.poll();
            }
            rooms.add(lectures.get(i).end);
        }

        System.out.println(rooms.size());
    }

    private static class Lecture {
        private final int start;
        private final int end;

        public Lecture(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private static class LectureComparator implements Comparator<Lecture> {
        @Override
        public int compare(Lecture l1, Lecture l2) {
            if (l1.start == l2.start) {
                return l1.end - l2.end;
            }
            return l1.start - l2.start;
        }
    }
}
