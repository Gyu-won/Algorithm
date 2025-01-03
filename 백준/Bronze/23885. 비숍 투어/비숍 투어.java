import java.awt.*;
import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int startRow = Integer.parseInt(st.nextToken());
		int startCol = Integer.parseInt(st.nextToken());
		Point startPoint = new Point(startRow, startCol);

		st = new StringTokenizer(br.readLine());
		int endRow = Integer.parseInt(st.nextToken());
		int endCol = Integer.parseInt(st.nextToken());
		Point endPoint = new Point(endRow, endCol);

		if (n == 1 || m == 1) {
			if (!startPoint.samePos(endPoint)) {
				System.out.println("NO");
				return;
			}
		}
		
		if (startPoint.canMove(endPoint)){
			System.out.println("YES");
		}else{
			System.out.println("NO");
		}
	}

	static class Point {
		private final int r;
		private final int c;

		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

		private boolean canMove(Point point) {
			int rowDifference = Math.abs(this.r - point.r);
			int colDifference = Math.abs(this.c - point.c);
			return (rowDifference % 2) == (colDifference % 2);
		}

		private boolean samePos(Point point) {
			return this.r == point.r && this.c == point.c;
		}
	}
}

// 입력
// r의 차이 % 2 == c의 차이 %2

// 09:33
