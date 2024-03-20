import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static char[] vowels = new char[]{'a', 'e', 'i', 'o', 'u'};

    public static void main(String[] args) throws IOException {
        // l, c 입력 (3-15)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int l = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        // alphabet 입력
        String[] alphabets = new String[c];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < c; i++) {
            alphabets[i] = st.nextToken();
        }

        // alphabet 정렬
        Arrays.sort(alphabets);

        // dfs()
        List<String> passwords = new ArrayList<>();
        dfs(0, l, "", alphabets, passwords);

        // 모음 1개, 자음 1개인것만 출력
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < passwords.size(); i++) {
            String password = passwords.get(i);
            if (isValid(l, password)) {
                result.append(password);
                result.append("\n");
            }
        }
        System.out.println(result.toString().trim());
    }

    private static boolean isValid(int l, String password) {
        int vowelNum = 0, consonantNum = 0;
        for (char alphabet : password.toCharArray()) {
            boolean isVowel = false;
            for (int i = 0; i < 5; i++) {
                if (alphabet == vowels[i]) {
                    isVowel = true;
                    break;
                }
            }
            if (isVowel){
                vowelNum++;
            }else{
                consonantNum++;
            }
        }

        return vowelNum >= 1 && consonantNum >= 2;
    }

    private static void dfs(int now, int l, String password, String[] alphabets, List<String> passwords) {
        if (l == password.length()) {
            passwords.add(password);
            return;
        }
        for (int i = now; i < alphabets.length; i++) {
            dfs(i + 1, l, password + alphabets[i], alphabets, passwords);
        }
    }
}

// 서로 다른 L개의 알파벳 소문자, 최소 모음 1개, 최소 자음 2개
// 오름차순
// 문자의 종류는 c가지
// 가능성 있는 암호 모두 구하기