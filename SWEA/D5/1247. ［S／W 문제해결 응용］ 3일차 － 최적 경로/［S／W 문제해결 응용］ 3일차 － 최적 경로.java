import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Solution {
    private static int numberOfCustomer, distance, minDistance;
	private static boolean[] visited;
	private static Point home;
	private static List<Point> customerHomeList;
    
    public static void main(String[] args) throws IOException {
        StringBuilder result = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int numberOfTest = Integer.parseInt(br.readLine());

		for (int i = 0; i < numberOfTest; i++) {
			numberOfCustomer = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());

			visited = new boolean[numberOfTest];

			int companyR = Integer.parseInt(st.nextToken());
			int companyC = Integer.parseInt(st.nextToken());
			Point company = new Point(companyR, companyC);

			int homeR = Integer.parseInt(st.nextToken());
			int homeC = Integer.parseInt(st.nextToken());
			home = new Point(homeR, homeC);

			customerHomeList = new ArrayList<>();
			for (int j = 0; j < numberOfCustomer; j++) {
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				customerHomeList.add(new Point(r, c));
			}

			distance = 0;
			minDistance = Integer.MAX_VALUE;

			dfs(company, 0);
			result.append("#");
            result.append(i+1);
            result.append(" ");
			result.append(minDistance);
			result.append("\n");
		}

		System.out.println(result.toString().trim());
	}

	private static void dfs(Point from, int numberOfVisit) {
		if (numberOfVisit == numberOfCustomer) {
			int distanceToHome = from.calculateDistanceTo(home);
			distance += distanceToHome;
			minDistance = Math.min(distance, minDistance);
			distance -= distanceToHome;
			return;
		}

		for (int i = 0; i < numberOfCustomer; i++) {
			if (!visited[i]) {
				Point nextPoint = customerHomeList.get(i);
				int addedDistance = from.calculateDistanceTo(nextPoint);

				visited[i] = true;
				distance += addedDistance;
				dfs(nextPoint, numberOfVisit + 1);
				distance -= addedDistance;
				visited[i] = false;
			}
		}
	}

	private static class Point {
		private final int r;
		private final int c;

		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

		private int calculateDistanceTo(Point p) {
			return Math.abs(this.r - p.r) + Math.abs(this.c - p.c);
		}
	}
}