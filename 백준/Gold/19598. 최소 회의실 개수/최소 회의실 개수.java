import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public int solution() throws IOException {
        // n을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // Conference[n] a을 입력받는다.
        Conference[] a = new Conference[n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            a[i] = new Conference(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(a, new ConferenceComparator());

        // PriorityQueue<Integer> pq를 선언한다.
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(a[0].end);

        // a를 하나씩 반복하면서
        for (int i = 1; i < n; i++) {
            // q에서 peek 해서 끝시간 <= a 시작시간 이면 pop 하고 a의 끝시간을 push
            if (pq.peek() <= a[i].start) {
                pq.poll();
            }

            // 끝시간 > a 시작 시간이면 그냥 a를 push
            pq.offer(a[i].end);
        }

        // q의 크기를 출력한다.
        return pq.size();
    }

    private class Conference {
        private final int start;
        private final int end;

        Conference(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private class ConferenceComparator implements Comparator<Conference> {
        // a를 시작시간으로 오름차순 정렬
        @Override
        public int compare(Conference c1, Conference c2) {
            return c1.start - c2.start;
        }
    }
}

// 설계 시간: 13:27
// 풀이 시간:

//0. 문제요약
// n개의 회의를 진행하는 최소 회의실 개수
// 동시에 2개 회의 진행 불가, 중간 중단 불가, 끝나자마자 시작은 가능
// 시작시간 < 끝나는 시간

//2. 시간복잡도: O(nlogn)

//3. 이 로직으로 예제 문제 손으로 풀기

//4. 경계값 체크 후 코드 구현

//틀렸을 때
//1. 이분탐색으로 틀린부분 찾고, 로직 생각 하기
//2. 프린트해보기, 간단한 예외케이스를 만들기

