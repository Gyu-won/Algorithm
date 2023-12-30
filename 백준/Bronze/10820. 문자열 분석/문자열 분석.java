import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<String> results = new ArrayList<>();
        String string;
        while ((string = br.readLine()) != null) {
            int[] numberOfElement = new int[4];
            for (char element : string.toCharArray()) {
                if (element >= 'a' && element <= 'z') {
                    numberOfElement[0]++;
                } else if (element >= 'A' && element <= 'Z') {
                    numberOfElement[1]++;
                } else if (element >= '0' && element <= '9') {
                    numberOfElement[2]++;
                } else if (element == ' ') {
                    numberOfElement[3]++;
                }
            }

            StringBuilder result = new StringBuilder();
            for (int value : numberOfElement) {
                result.append(value + " ");
            }
            results.add(result.toString().trim());
        }
        System.out.println(String.join("\n", results));
    }
}