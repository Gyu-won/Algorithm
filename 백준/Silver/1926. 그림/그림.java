import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	private static final int numberOfDirection = 4;
	private static final int[] rowDirection = {-1, 0, 1, 0};
	private static final int[] colDirection = {0, -1, 0, 1};

	private static int paperRow;
	private static int paperCol;
	private static boolean[][] visited;
	private static int[][] paper;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] paperSize = br.readLine().split(" ");

		paperRow = Integer.parseInt(paperSize[0]);
		paperCol = Integer.parseInt(paperSize[1]);

		visited = new boolean[paperRow][paperCol];
		paper = new int[paperRow][paperCol];

		for (int currentRow = 0; currentRow < paperRow; currentRow++) {
			String[] line = br.readLine().split(" ");
			for (int currentCol = 0; currentCol < paperCol; currentCol++) {
				paper[currentRow][currentCol] = Integer.parseInt(line[currentCol]);
			}
		}

		int numberOfDrawing = 0;
		int maxArea = 0;
		for (int currentRow = 0; currentRow < paperRow; currentRow++) {
			for (int currentCol = 0; currentCol < paperCol; currentCol++) {
				int area = calculateArea(currentRow, currentCol, 0);
				if (area > 0) {
					numberOfDrawing++;
					maxArea = Math.max(maxArea, area);
				}
			}
		}

		System.out.println(numberOfDrawing);
		System.out.println(maxArea);
	}

	public static int calculateArea(int row, int col, int area) {
		if (row >= 0 && row < paperRow && col >= 0 && col < paperCol && !visited[row][col] && paper[row][col] == 1) {
			visited[row][col] = true;
			area++;

			for (int direction = 0; direction < numberOfDirection; direction++) {
				int nextRow = row + rowDirection[direction];
				int nextCol = col + colDirection[direction];
				area = calculateArea(nextRow, nextCol, area);
			}
		}
		return area;
	}
}

// dfs: O(4 * n * m)
// 하나씩 방문 하여 1이고, visited = false이면 그림개수 증가, 해당 건에서 2가지 방향으로 가며 넓이 계산
