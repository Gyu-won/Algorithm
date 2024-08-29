import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		Set<Integer> numbers = new HashSet<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int index = 0; index < n; index++) {
			numbers.add(Integer.parseInt(st.nextToken()));
		}

		System.out.println("YES");
		int maxNumber = numbers.stream().mapToInt(Integer::intValue).max().orElse(0);
		System.out.println(maxNumber * 100 + maxNumber * 10 + maxNumber);
	}
}

// 21:45

// N 입력
// numbers 입력
// 숫자가 0만 있으면 NO, 아니면 YES
// 1만 있으면 111, 0이 아닌 수를 2번 작성
// 1이면 3번 작성
// 최대 숫자 10의 11승 -> 소수가 아닌 수를 다 찾으면 제곱으로 따지면 10 6승
