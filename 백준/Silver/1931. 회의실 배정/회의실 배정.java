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
        // 회의시간들을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Meeting> meetings = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            meetings.add(new Meeting(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken())));
        }

        // 가장 빨리 끝나는 순서대로 정렬
        Collections.sort(meetings, new MeetingComparator());

        // 정렬된 배열에서 회의가 가능하다면 회의를 진행한다.
        int count = 0;
        long currentTime = 0;
        for (Meeting meeting : meetings) {
            if (meeting.isPossible(currentTime)) {
                currentTime = meeting.readEnd();
                count++;
            }
        }

        // 결과를 출력한다.
        System.out.println(count);
    }

    private static class Meeting {
        private final long start;
        private final long end;

        public Meeting(long start, long end) {
            this.start = start;
            this.end = end;
        }

        public boolean isPossible(long currentTime) {
            return start >= currentTime;
        }

        public long readEnd() {
            return end;
        }
    }

    private static class MeetingComparator implements Comparator<Meeting> {
        @Override
        public int compare(Meeting m1, Meeting m2) {
            if (m1.end == m2.end) {
                return Long.compare(m1.start, m2.start);
            }
            return Long.compare(m1.end, m2.end);
        }
    }
}
