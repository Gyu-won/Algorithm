import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        //입력을 5개 다 받아서 String 배열로 저장한다
        int maxLength = 0;
        List<String> inputWords = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            String input = bf.readLine();
            if (input.length() > maxLength) {
                maxLength = input.length();
            }
            inputWords.add(input);
        }

        //세로로 읽은 것을 다시 String 배열로 저장한다
        List<String> verticalWords = new ArrayList<>();
        for (int index = 0; index < maxLength; index++) {
            StringBuilder verticalWord = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                if (inputWords.get(i).length() > index) {
                    verticalWord.append(inputWords.get(i).charAt(index));
                }
            }
            verticalWords.add(verticalWord.toString());
        }

        // 결과 출력
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.join("", verticalWords));
        bw.flush();
        bf.close();
        bw.close();
    }
}