
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        //N을 입력받는다
        int N = Integer.parseInt(bf.readLine());

        // 색종이들을 입력받는다
        int[][] paper = new int[101][101];

        for (int i = 0; i < N; i++) {
            String[] colorPaperPoints = bf.readLine().split(" ");
            int xStart = Integer.parseInt(colorPaperPoints[0]);
            int yStart = Integer.parseInt(colorPaperPoints[1]);

            for (int x = xStart; x < xStart + 10; x++) {
                for (int y = yStart; y < yStart + 10; y++) {
                    paper[x][y] = 1;
                }
            }
        }

        // 겹치는부분 색종이 넓이를 계산한다.
        int totalSize = 0;
        for (int i = 1; i < 101; i++) {
            for (int j = 1; j < 101; j++) {
                if (paper[i][j] == 1) {
                    totalSize += 1;
                }
            }
        }

        // 결과 출력
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(totalSize));
        bw.flush();
        bf.close();
        bw.close();
    }
}