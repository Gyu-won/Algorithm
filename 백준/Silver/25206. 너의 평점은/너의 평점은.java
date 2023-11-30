import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        //평점을 저장한다.
        Map<String, Double> score = Map.of(
                "A+", 4.5,
                "A0", 4.0,
                "B+", 3.5,
                "B0", 3.0,
                "C+", 2.5,
                "C0", 2.0,
                "D+", 1.5,
                "D0", 1.0,
                "F", 0.0
        );

        //입력을 20 번 받으면서 학점의 합, 학점 * 평점의 합을 계산한다.
        double totalGrade = 0.0;
        double totalGpa = 0.0;

        for (int i = 0; i < 20; i++) {
            String[] input = bf.readLine().split(" ");
            double grade = Double.parseDouble(input[1]);

            if (!input[2].equals("P")) {
                totalGrade += grade;
                totalGpa += grade * score.get(input[2]);
            }
        }

        // 결과 출력
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(totalGpa / totalGrade));
        bw.flush();
        bf.close();
        bw.close();
    }
}