import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final int[] buttons = new int[]{300, 60, 10};

    public static void main(String[] args) throws IOException {
        // T를 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        // T를 가장 큰 시간 버튼부터 누른다.
        String result = calculateOptimizeButtonsPress(T);

        // 결과를 출력한다.
        System.out.println(result.trim());
    }

    private static String calculateOptimizeButtonsPress(int time) {
        StringBuilder result = new StringBuilder();
        for (int button : buttons) {
            result.append(time / button);
            result.append(" ");
            time %= button;
        }

        if (time > 0) {
            return "-1";
        }
        return result.toString();
    }
}