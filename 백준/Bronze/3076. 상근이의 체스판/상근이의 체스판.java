import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int boardHeight = Integer.parseInt(st.nextToken());
		int boardWidth = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int pieceHeight = Integer.parseInt(st.nextToken());
		int pieceWidth = Integer.parseInt(st.nextToken());

		System.out.println(createChessBoard(boardHeight, boardWidth, pieceHeight, pieceWidth));
	}

	private static String createChessBoard(int boardHeight, int boardWidth, int pieceHeight, int pieceWidth) {
		StringBuilder chessBoard = new StringBuilder();
		for (int r = 0; r < boardHeight; r++) {
			for (int a = 0; a < pieceHeight; a++) {
				for (int c = 0; c < boardWidth; c++) {
					for (int b = 0; b < pieceWidth; b++) {
						if (r % 2 == c % 2) {
							chessBoard.append('X');
						} else {
							chessBoard.append('.');
						}
					}
				}
				chessBoard.append("\n");
			}
		}
		return chessBoard.toString().trim();
	}
}

// for문을 돌면서 해결 (r -> a ->
