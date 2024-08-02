import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int firstNumber = Integer.parseInt(st.nextToken());
		int exponent = Integer.parseInt(st.nextToken());

		System.out.println(getNumberOfUniqueNumber(firstNumber, exponent));
	}

	private static int getNumberOfUniqueNumber(int firstNumber, int exponent) {
		List<Integer> sequence = new ArrayList<>();
		sequence.add(firstNumber);

		int numberOfUniqueNumber = -1;
		while (true) {
			int number = sequence.get(sequence.size() - 1);
			String parsedNumber = String.valueOf(number);

			int nextNumber = 0;
			for (int digit = 0; digit < parsedNumber.length(); digit++) {
				int digitNumber = parsedNumber.charAt(digit) - 48;
				nextNumber += (int)Math.pow(digitNumber, exponent);
			}

			numberOfUniqueNumber = sequence.indexOf(nextNumber);
			if (numberOfUniqueNumber > -1) {
				break;
			}
			sequence.add(nextNumber);
		}
		return numberOfUniqueNumber;
	}
}

// D[n] 계산 후 indexOf로 있는지 확인
// 나올 수 있는 최대 숫자는 60,0000
// indexOf의 최대는 60,000*30,000 = 1800000000 -> 2초 이내 가능
