import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static String T;
    private static int flag = 0;

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public long solution() throws IOException {
        // S를 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();

        // T를 입력받는다.
        T = br.readLine();

        // 완탐
        // 둘 중 하나를 택하고 T의 길이까지 한 후 같은지 비교
        find(S);

        return flag;
    }

    private void find(String s) {
        if (s.length() == T.length()) {
            if (T.contentEquals(s)) {
                flag = 1;
            }
            return;
        }

        StringBuilder temp = new StringBuilder(s + "A");
        if (T.contains(temp) || T.contains(temp.reverse().toString())) {
            find(s + "A");
        }

        temp = new StringBuilder(s + "B");
        if (T.contains(temp) || T.contains(temp.reverse().toString())) {
            find(new StringBuilder(s + "B").reverse().toString());
        }
    }
}

// 설계 시간: 11:44 - 12:01
// 풀이 시간:

//0. 문제요약
// S와 T가 주어졌을 때 S를 T로 바꾸기
// A를 제일 뒤에 추가하거나, B를 추가하고 뒤집는 것
// S를 T로 만들 수 있으면 1, 없으면 0

//2. 시간복잡도:

//3. 이 로직으로 예제 문제 손으로 풀기

//4. 경계값 체크 후 코드 구현
