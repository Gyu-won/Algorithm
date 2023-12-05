import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        // 풀이:
        // 가장 적게 들고가려면 5kg를 가장 많이 들고가면 된다. 5키로 최대로 할당한 후 하나씩 줄이면서 비교하면 될 것이다.
        // N이 5000보다 작기 때문에 5키로 박스의 최대값은 1000이고, 3 키로 박스의 최대는 1666 이기 때문에 최대 비교할 횟수는
        // 166000 이므로 1초 이내 계산이 가능하다.

        // N을 입력받는다.
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());

        // 최대한 많이 할당할 수 있는 5키로의 개수를 구한다.
        int totalNumberOfBox = -1;

        // 5키로를 하나씩 줄이면서 나누어 떨어질때까지 계산한다.
        for (int numberOfFiveKilogram = N / 5; numberOfFiveKilogram >= 0; numberOfFiveKilogram--) {
            int leftWeight = N - numberOfFiveKilogram * 5;
            if (leftWeight % 3 == 0) {
                totalNumberOfBox = numberOfFiveKilogram + leftWeight / 3;
                break;
            }
        }

        // 결과 출력
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(totalNumberOfBox));
        bw.flush();
        bf.close();
        bw.close();
    }

}