import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        // n 입력 (1-20)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // hanoi(n) 구하기
        System.out.println(hanoiNumber(n));
        System.out.println(hanoi(n).toString().trim());
    }

    private static int hanoiNumber(int n) {
        if (n == 1) {
            return 1;
        }
        return hanoiNumber(n - 1) * 2 + 1;
    }

    private static StringBuilder hanoi(int n) {
        if (n == 1) {
            return new StringBuilder("1 3\n");
        }
        return stackTwo(hanoi(n - 1)).append("1 3\n").append(stackThree(hanoi(n - 1)));
    }

    private static StringBuilder stackTwo(StringBuilder hanoi) {
        for (int i = 0; i < hanoi.length(); i++) {
            if (hanoi.charAt(i) == '2') {
                hanoi.setCharAt(i, '3');
            } else if (hanoi.charAt(i) == '3') {
                hanoi.setCharAt(i, '2');
            }
        }
        return hanoi;
    }

    private static StringBuilder stackThree(StringBuilder hanoi) {
        for (int i = 0; i < hanoi.length(); i++) {
            if (hanoi.charAt(i) == '1') {
                hanoi.setCharAt(i, '2');
            } else if (hanoi.charAt(i) == '2') {
                hanoi.setCharAt(i, '1');
            }
        }
        return hanoi;
    }
}

// 19:37 -


