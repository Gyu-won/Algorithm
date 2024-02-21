import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // str1을 입력받는다. char[]
        char[] str1 = br.readLine().toCharArray();

        // str2를 입력받는다. char[]
        char[] str2 = br.readLine().toCharArray();

        // int lcs를 선언한다.
        int[] lcs;
        if (str1.length < str2.length) {
            lcs = findLCS(str2, str1);
        } else {
            lcs = findLCS(str1, str2);
        }

        int maxValue = findMaxLength(lcs);

        System.out.println(maxValue);
    }

    private static int findMaxLength(int[] array) {
        int maxValue = 0;
        for (int i = 0; i < array.length; i++) {
            maxValue = Math.max(maxValue, array[i]);
        }
        return maxValue;
    }

    private static int[] findLCS(char[] str1, char[] str2) {
        int size = str2.length;
        int[] lcs = new int[size];

        for (char findChar : str1) {
            int maxCount = 0;
            for (int i = 0; i < str2.length; i++) {
                if (str2[i] == findChar) {
                    int temp = maxCount;
                    maxCount = Math.max(maxCount, lcs[i]);
                    lcs[i] = temp + 1;
                } else {
                    maxCount = Math.max(maxCount, lcs[i]);
                }
            }
        }

        return lcs;
    }
}

// 요약
// 부분수열 다 구하면 O(2^n) 안됨
// O(n^2 * logn) 까지는 가능

//0.4초: O(n^2)

// 09:15