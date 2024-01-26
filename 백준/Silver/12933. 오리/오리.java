import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    private static final Map<Character, Integer> sequence = Map.of(
            'q', 0,
            'u', 1,
            'a', 2,
            'c', 3,
            'k', 4);

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    public void solution() throws IOException {
        // sound를 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] sounds = br.readLine().toCharArray();

        if (sounds.length % 5 != 0) {
            System.out.println(-1);
        } else {
            System.out.println(calculateDuckNumber(sounds));
        }
    }

    private int calculateDuckNumber(char[] sounds) {
        List<Integer> ducks = new ArrayList<>();

        for (char sound : sounds) {
            // 문자를 하나씩 돌면서 List<Integer> ducks 들어갈 수 있으면 값을 update 한다.
            int soundNumber = sequence.get(sound);
            int i;
            for (i = 0; i < ducks.size(); i++) {
                if ((ducks.get(i) + 1) % 5 == soundNumber) {
                    ducks.set(i, soundNumber);
                    break;
                }
            }
            if (i == ducks.size()) {
                // 값이 들어갈 수 없고 문자가 'q'라면 새로운 ducks에 추가한다.
                if (soundNumber == 0) {
                    ducks.add(0);
                } else {
                    // 값이 들어갈 수 없고 문자가 'q'가 아니면 break 하고 -1을 출력한다.
                    return -1;
                }
            }
        }
        
        for (int duck: ducks){
            if (duck != 4){
                return -1;
            }
        }
        
        return ducks.size();
    }
}

// 알고리즘: 떠오르는 알고리즘이 없고 길이가 2500 이하이기 때문에 단순 구현하면 된다.
// 문자를 하나씩 받으면서 해당 값이 들어갈곳이 있으면 넣고, 없으면서 값이 q이면 새로운 값을 추가한다.

// 시간복잡도: O(2500 * 500)

// 정수 범위
