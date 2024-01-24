import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // sizeA와 sizeB를 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int sizeA = Integer.parseInt(st.nextToken());
        int sizeB = Integer.parseInt(st.nextToken());

        // A와 B를 입력받는다.
        int[] arrayA = new int[sizeA];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < sizeA; i++) {
            arrayA[i] = Integer.parseInt(st.nextToken());
        }

        int[] arrayB = new int[sizeB];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < sizeB; i++) {
            arrayB[i] = Integer.parseInt(st.nextToken());
        }

        // 하나씩 꺼내면서 크기비교 후 작은것을 넣고 새로 꺼내는 작업을 반복한다.
        StringBuilder answer = new StringBuilder();

        int indexA = 0, indexB = 0;
        while (indexA < sizeA && indexB < sizeB) {
            if (arrayA[indexA] < arrayB[indexB]) {
                answer.append(arrayA[indexA++]);
            } else {
                answer.append(arrayB[indexB++]);
            }
            answer.append(" ");
        }

        // 한쪽이 끝나면 나머지 한쪽의 배열을 뒤에 붙인다.
        for (int i = indexA; i < sizeA; i++) {
            answer.append(arrayA[i]);
            answer.append(" ");
        }

        for (int i = indexB; i < sizeB; i++) {
            answer.append(arrayB[i]);
            answer.append(" ");
        }

        System.out.println(answer.toString().trim());
    }
}

// 알고리즘: 입력을 받아 queue에 저장 후 크기비교를 통해 결과를 만들면 됨.

// 시간복잡도: O(N+M)

// 정수 범위 (int)
