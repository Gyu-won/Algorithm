import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int h, w;

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public int solution() throws IOException {
        // h와 w를 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int input1 = Integer.parseInt(st.nextToken());
        int input2 = Integer.parseInt(st.nextToken());

        if (input1 < input2) {
            h = input1;
            w = input2;
        } else {
            h = input2;
            w = input1;
        }

        // n을 입력받는다.
        int n = Integer.parseInt(br.readLine());

        // n만큼 Sticker를 생성한다.(짧은 길이, 긴길이, 넓이)
        Sticker[] stickers = new Sticker[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            stickers[i] = new Sticker(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        // 조합을 구해서 둘의 조합이 가능하다면 max와 비교하여 크면 넣는다.
        int maxArea = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isValid(stickers[i], stickers[j])) {
                    maxArea = Math.max(maxArea, stickers[i].area + stickers[j].area);
                }
                int side1 = stickers[i].x + stickers[j].x;
                int side2 = Math.max(stickers[i].y, stickers[j].y);
                if (Math.max(side1, side2) <= w && Math.min(side1, side2) <= h) {
                }
            }
        }

        // max를 출력한다.
        return maxArea;
    }

    private boolean isValid(Sticker s1, Sticker s2) {
        return canDetach(s1.x + s2.x, Math.max(s1.y, s2.y)) || canDetach(s1.x + s2.y, Math.max(s1.y, s2.x))
                || canDetach(s1.y + s2.x, Math.max(s1.x, s2.y)) || canDetach(s1.y + s2.y, Math.max(s1.x, s2.x));
    }

    private boolean canDetach(int side1, int side2) {
        return Math.max(side1, side2) <= w && Math.min(side1, side2) <= h;
    }

    static class Sticker {
        private final int x;
        private final int y;
        private final int area;

        private Sticker(int x, int y) {
            this.x = x;
            this.y = y;
            this.area = x * y;
        }
    }
}

// 알고리즘: 스티커 중 2개를 선택하면 되는데 최대를 선택해야한다.
// 같이 붙일 수 있는 조합을 구하고, 그중에서 넒이가 최대인 것을 구해야한다.
// NC2를 하되 최솟값 2개 더한거랑 최대값중 최대값이 더 긴 변이랑, 작은 값이 더 짧은 변보다 짧아야 한다.

// 시간복잡도: O(n*n + n)

// 정수 범위