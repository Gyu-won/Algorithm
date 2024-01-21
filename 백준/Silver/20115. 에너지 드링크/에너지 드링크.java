import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // 드링크의 개수를 입력받는다 (int)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numberOfDrink = Integer.parseInt(br.readLine());

        // 드링크를 입력받으면서 전체 합(long)과 최대 드링크(int)를 계산한다.
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        long totalDrink = 0;
        int maxDrink = 0;
        for (int i = 0; i < numberOfDrink; i++) {
            int quantityOfDrink = Integer.parseInt(st.nextToken());
            totalDrink += quantityOfDrink;
            maxDrink = Math.max(maxDrink, quantityOfDrink);
        }

        // 최대 드링크를 제외한 전체 합을 2로 나누고 최대 드링크에 더한다. (long)
        long pourDrink = totalDrink - maxDrink;
        if (pourDrink % 2 == 0) {
            System.out.println(pourDrink / 2 + maxDrink);
        } else {
            System.out.println(pourDrink / 2.0 + maxDrink);
        }
    }
}

// 최대가 되려면 제일 적게 흘리면 된다.
// 작은거를 가장 큰거에 부워야한다.

// 알고리즘: 단순 구현

// 시간복잡도: O(n)

// 정수 범위