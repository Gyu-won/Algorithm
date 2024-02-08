import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public int solution() throws IOException {
        // r, c, k를 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // 배열 int[100][100] a를 입력받는다.
        int[][] a = new int[100][100];
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 3; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int rSize = 3;
        int cSize = 3;

        // 100번 반복한다. O(100)
        for (int t = 0; t <= 100; t++) {
            // a[r-1][c-1] == k이면 count를 리턴한다.
            if (a[r - 1][c - 1] == k) {
                return t;
            }

            // r연산인지 c연산인지 정한다. (rSize, cSize)
            if (rSize >= cSize) {
                // 각 행 또는 열을 돌며 등장 횟수를 구한다 O(100 * 100)
                int maxCSize = 0;
                for (int i = 0; i < rSize; i++) {
                    Map<Integer, Integer> map = new HashMap<>();

                    for (int j = 0; j < cSize; j++) {
                        if (a[i][j] != 0) {
                            map.put(a[i][j], map.getOrDefault(a[i][j], 0) + 1);
                        }
                    }

                    // 0이 아닌 등장
                    List<Number> numbers = new ArrayList<>();
                    for (int key : map.keySet()) {
                        numbers.add(new Number(key, map.get(key)));
                    }

                    // 정렬하여 값을 넣는다 O(100log100), 100까지만
                    Collections.sort(numbers, new NumberComparator());
                    for (int n = 0; n < numbers.size(); n++) {
                        a[i][2 * n] = numbers.get(n).number;
                        a[i][2 * n + 1] = numbers.get(n).time;
                    }
                    for (int n = 2 * numbers.size(); n < 100; n++) {
                        a[i][n] = 0;
                    }

                    // rSize와 cSize를 update 한다.
                    maxCSize = Math.max(maxCSize, numbers.size() * 2);
                }
                cSize = maxCSize;
            } else {
                int maxRSize = 0;
                for (int j = 0; j < cSize; j++) {
                    Map<Integer, Integer> map = new HashMap<>();
                    for (int i = 0; i < rSize; i++) {
                        if (a[i][j] != 0) {
                            map.put(a[i][j], map.getOrDefault(a[i][j], 0) + 1);
                        }

                    }

                    // 0이 아닌 등장
                    List<Number> numbers = new ArrayList<>();
                    for (int key : map.keySet()) {
                        numbers.add(new Number(key, map.get(key)));
                    }

                    // 정렬하여 값을 넣는다 O(100log100), 100까지만
                    Collections.sort(numbers, new NumberComparator());
                    for (int n = 0; n < numbers.size(); n++) {
                        a[2 * n][j] = numbers.get(n).number;
                        a[2 * n + 1][j] = numbers.get(n).time;
                    }
                    for (int n = 2 * numbers.size(); n < 100; n++) {
                        a[n][j] = 0;
                    }

                    // rSize와 cSize를 update 한다.
                    maxRSize = Math.max(maxRSize, numbers.size() * 2);
                }
                rSize = maxRSize;
            }
        }

        return -1;
    }

    static class Number {
        private final int number;
        private final int time;

        Number(int number, int time) {
            this.number = number;
            this.time = time;
        }
    }

    static class NumberComparator implements Comparator<Number> {
        @Override
        public int compare(Number n1, Number n2) {
            if (n1.time == n2.time) {
                return n1.number - n2.number;
            }
            return n1.time - n2.time;
        }
    }
}

// 설계 시간: 14:27 -
// 풀이 시간:

//0. 문제요약
// 3*3 배열
// r: r이 더 길거나 같으면 행기준으로 정렬
// c: c가 더 길면 열기준으로 정렬
// 정렬 시에는 등장횟수 오름차순, 숫자 오름차순으로 정렬 (수, 등장횟수로 넣음)
// 행, 열이 커질텐데 r이면 가장 큰 열 기준으로 길이가 변하고, 나머지는 0이 채워진다.
// 정렬 시 0은 무시, 100넘어가면 100개이상 뒤에는 버림
// a[r][c]의 값이 k가 되기위한 최소시간
// 100번해도 k가 안되면 -1을 리턴

//2. 시간복잡도: O(100 * 100 * 100)

//3. 이 로직으로 예제 문제 손으로 풀기

//4. 경계값 체크 후 코드 구현
