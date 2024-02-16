import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    private static int[] parent;

    public static void main(String[] args) throws IOException {
        System.out.println(new Solution().solution());
    }

    static class Solution {
        public String solution() throws IOException {
            // t를 입력받는다.
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int t = Integer.parseInt(br.readLine());

            StringBuilder result = new StringBuilder();
            for (int n = 0; n < t; n++) {
                // k를 입력받는다. (3-1,000,000)
                int k = Integer.parseInt(br.readLine());

                // long pq 를 입력받는다. (1-10000)
                List<Long> pages = new ArrayList<>();
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int i = 0; i < k; i++) {
                    pages.add(Long.parseLong(st.nextToken()));
                }
                Collections.sort(pages);

                PriorityQueue<Long> pq = new PriorityQueue<>(pages);

                long cnt = 0;
                // pq 크기가 1보다 클 떄까지
                while (pq.size() > 1) {
                    long p1 = pq.poll();
                    long p2 = pq.poll();
                    cnt += p1 + p2;
                    pq.offer(p1 + p2);
                }

                result.append(cnt);
                result.append("\n");
            }
            return result.toString().trim();
        }
    }
}

// 요약
// conquer 할 때 비용은 두 파일의 크기 합
// 파일 합칠떄의 최소비용
// long으로 받기작은거부터 2개씩 합치면 된다.
// pq 사용 (long)

// 10:54  -

// 2초: O(klogk * t)