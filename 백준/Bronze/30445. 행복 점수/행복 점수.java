import java.awt.*;
import java.sql.SQLOutput;
import java.time.Month;
import java.util.*;
import java.io.*;
import java.util.List;

class Main {

	private static final List<Character> happy = List.of('H', 'A', 'P', 'Y');
	private static final List<Character> sad = List.of('S', 'A', 'D');

	private static int happyScore = 0;
	private static int sadScore = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		while(st.hasMoreTokens()){
			calculateScore(st.nextToken());
		}
		System.out.printf("%.2f", convertToHappinessLevel());
	}

	private static void calculateScore(String word) {
		for (char alphabet : word.toCharArray()) {
			if (happy.contains(alphabet)) {
				happyScore++;
			}
			if (sad.contains(alphabet)) {
				sadScore++;
			}
		}
	}

	private static double convertToHappinessLevel(){
		if (happyScore == 0 && sadScore == 0) {
			return 50.00;
		}

		double happinessLevel= Math.round((double) happyScore * 10000 / ((double) happyScore + (double) sadScore));

		return happinessLevel / 100.00;
	}
}

// 입력
// 단어 넘기면서 행복 점수, 슬픔 점수 계산 O(80)
	// 단어의 각 글자 반복 O(20)
		// list.contains 메서드로 확인: O(7)
// 점수 계산(2째 자리 반올림)
// 둘다 0이면 0.5

// 11:10
// 둘다 0이면 행복 지수 0.5
