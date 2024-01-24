import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long sum = Long.parseLong(br.readLine());

        int count = 0;
        for (int i = 1; i * 2 < sum; i++) {
            sum -= i;
            count++;
        }
        System.out.println(count + 1);
    }
}

// 알고리즘: N이 최대이기 위해서는 작은수들이 많아야한다. 즉 지금 넣을 수 있는 최소한의 수를 넣으면 된다.

// 시간복잡도: O(N)

// 정수 범위: count는 int, sum은 long
