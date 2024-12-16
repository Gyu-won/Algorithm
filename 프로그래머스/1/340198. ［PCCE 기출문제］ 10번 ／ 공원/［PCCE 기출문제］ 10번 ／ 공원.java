import java.util.*;

class Solution {
    public int solution(int[] mats, String[][] park) {
        int answer = -1;
        
        Arrays.sort(mats);
        
        for (int i = 0; i < mats.length; i++) {
            int matLength = mats[i];
            
            if (!canLayout(matLength, park)) {
                break;
            }
            answer = matLength;
        }
        
        return answer;
    }
    
    private static boolean canLayout(int matLength, String[][] park) {
        for (int r = 0; r <= park.length - matLength; r++) {
            for (int c = 0; c <= park[0].length - matLength; c++) {
                if (hasSpace(matLength, r, c, park)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private static boolean hasSpace(int matLength, int r, int c, String[][] park) {
        for (int y = r; y < r + matLength; y++) {
            for (int x = c; x < c + matLength; x++) {
                if (!park[y][x].equals("-1")) {
                    return false;   
                }
            }
        }
        return true;
    }
}

// 결과: 깔 수 있는 돗자리의 가장 긴 한 변 길이 (못하면 -1)

// 1. mats 정렬
// 2. canLayout 값 false 일때 까지
    // 2-1. 2차원 배열 돌면서 가장자리 값 정하기
    // 2-2. 가장자리 값에서 mat 길이 true 면 return true
        //해당 가장자리 값에서, mat 길이만큼 2차원 반복문으로 반복 하여 -1 이 아니면 return false
// 3. answer 값 변경

// O(20 * 50 * 50  * 50 * 50)