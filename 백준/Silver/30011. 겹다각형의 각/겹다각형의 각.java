import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int numberOfPolygons = Integer.parseInt(br.readLine());

		int[] polygons = new int[numberOfPolygons];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int number = 0; number < numberOfPolygons; number++) {
			polygons[number] = Integer.parseInt(st.nextToken());
		}
		System.out.println(calculateMaxDrawingScore(polygons));
	}

	private static int calculateMaxDrawingScore(int[] polygons) {
		int firstPolygonScore = 180 * (polygons[0] - 2);
		return Arrays.stream(polygons)
			.skip(1)
			.map(side -> 180 * side)
			.sum() + firstPolygonScore;
	}
}

// 1000 * 180 * 100 = 18000000