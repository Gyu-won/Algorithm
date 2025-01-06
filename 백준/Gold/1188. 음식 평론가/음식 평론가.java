import java.awt.*;
import java.sql.SQLOutput;
import java.time.Month;
import java.util.*;
import java.io.*;
import java.util.List;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int gcd;
		if (n > m) {
			gcd = calculateGcd(n, m);
		}else{
			gcd = calculateGcd(m, n);
		}

		System.out.println(m - gcd);
	}

	private static int calculateGcd(int a, int b) {
		if (b == 0) {
			return a;
		}
		return calculateGcd(b, a%b);
	}
}

// n, m 입력

// n % m, m 으로 변경 (n % 2 < m)
// 값: m - 최대공약수
