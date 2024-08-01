import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {

	private static final int numberOfDirection = 4;
	private static final int[] directionX = {-1, 0, 1, 0};
	private static final int[] directionY = {0, 1, 0, -1};
	private static int width, height, numberOfArea = 0;
	private static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		height = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		visited = new boolean[width][height];

		int numberOfRectangle = Integer.parseInt(st.nextToken());
		for (int areaNumber = 0; areaNumber < numberOfRectangle; areaNumber++) {
			st = new StringTokenizer(br.readLine());
			int leftX = Integer.parseInt(st.nextToken());
			int leftY = Integer.parseInt(st.nextToken());
			int rightX = Integer.parseInt(st.nextToken());
			int rightY = Integer.parseInt(st.nextToken());
			fillVisited(leftX, leftY, rightX, rightY);
		}

		List<Integer> areaList = calculateArea();

		StringBuilder result = new StringBuilder();
		result.append(numberOfArea);
		result.append("\n");
		result.append(areaList.stream()
			.map(String::valueOf)
			.collect(Collectors.joining(" "))
		);

		System.out.println(result.toString());
	}

	private static void fillVisited(int leftX, int leftY, int rightX, int rightY) {
		for (int x = leftX; x < rightX; x++) {
			for (int y = leftY; y < rightY; y++) {
				visited[x][y] = true;
			}
		}
	}

	private static List<Integer> calculateArea() {
		List<Integer> areaList = new ArrayList<>();
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (!visited[x][y]) {
					numberOfArea++;
					areaList.add(dfs(x, y, 0));
				}
			}
		}
		Collections.sort(areaList);
		return areaList;
	}

	private static int dfs(int x, int y, int area) {
		if (x < 0 || x >= width || y < 0 || y >= height || visited[x][y]) {
			return area;
		}

		area++;
		visited[x][y] = true;
		for (int direction = 0; direction < numberOfDirection; direction++) {
			int nextX = x + directionX[direction];
			int nextY = y + directionY[direction];
			area = dfs(nextX, nextY, area);
		}
		return area;
	}
}

// 몇개로 나누어지고, 넓이 각각 출력
// 색칠한 부분 방문 처리 O(k*n*m)
// 하나씩 돌면서 dfs로 처리 O(n*m*4)
// for문으로 찾을 때마다 count 증가
// bfs나 dfs로 넓이 계산
