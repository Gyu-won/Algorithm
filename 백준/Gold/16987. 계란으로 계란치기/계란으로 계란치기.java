import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static boolean[] broken;
    private static int maxBroken = 0;

    public static void main(String[] args) throws IOException {
        // n을 입력받는다 (1-8)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        // Egg를 입력받는다 (s, w)
        StringTokenizer st;
        Egg[] eggs = new Egg[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            eggs[i] = new Egg(s, w);
        }

        broken = new boolean[n];
        dfs(0, eggs);

        System.out.println(maxBroken);
    }

    private static void dfs(int current, Egg[] eggs) {
        if (current == n) {
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (broken[i]) {
                    count++;
                }
            }
            maxBroken = Math.max(count, maxBroken);
            return;
        }

        if (broken[current] || allBroken(current)) {
            dfs(current + 1, eggs);
        } else {
            for (int i = 0; i < n; i++) {
                if (i != current && !broken[i]) {
                    hit(current, i, eggs);
                    dfs(current + 1, eggs);
                    rollback(current, i, eggs);
                }
            }
        }
    }

    private static void hit(int current, int other, Egg[] eggs) {
        eggs[current].s -= eggs[other].w;
        eggs[other].s -= eggs[current].w;
        
        if (eggs[current].s < 1) {
            broken[current] = true;
        }

        if (eggs[other].s < 1) {
            broken[other] = true;
        }
    }

    private static void rollback(int current, int other, Egg[] eggs) {
        eggs[current].s += eggs[other].w;
        eggs[other].s += eggs[current].w;

        if (eggs[current].s > 0) {
            broken[current] = false;
        }

        if (eggs[other].s > 0) {
            broken[other] = false;
        }
    }

    private static boolean allBroken(int current) {
        for (int i = 0; i < n; i++) {
            if (i != current && !broken[i]) {
                return false;
            }
        }
        return true;
    }

    private static class Egg {
        private int s;
        private final int w;

        Egg(int s, int w) {
            this.s = s;
            this.w = w;
        }
    }
}

// 계란으로 계란을 치면 두 계란의 내구도가 상대 계란 무게만큼 깍인다.

// 왼쪽계란
// 안깨진 것 중 하나 치고, 내려놓는다. (계란이 꺠졌거나 다깨졌으면 안치고 내려놓는다)
// 그 다음 계란을 친다
// 최대 몇개의 계란을 꺨 수 있는지

// O(n!) 까지 가능
// 완탐하면 O((n-1)^n) = O(7^8) 은 O(2^24)이므로 가능
// dfs ,