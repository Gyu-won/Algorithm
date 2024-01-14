import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

class Solution {
    public int solution(String numbers) {
        // 모든 수의 조합 만들기
        Set<Integer> allPossibleNumbers = new HashSet<>();
        createPossibleNumbers(numbers, "", allPossibleNumbers);

        // 최대값까지 소수인지 확인하는 배열 만들기
        int maxNumber = Collections.max(allPossibleNumbers);
        boolean[] isNotPrime = new boolean[maxNumber + 1];
        isNotPrime[0] = true;
        isNotPrime[1] = true;
        findPrimeUntilMaxNumber(isNotPrime, maxNumber);

        // 해당 숫자가 소수인지 확인해서 개수를 증가
        int answer = 0;
        for (Integer number : allPossibleNumbers) {
            if (!isNotPrime[number]) {
                answer++;
            }
        }
        return answer;
    }

    private void findPrimeUntilMaxNumber(boolean[] isNotPrime, int maxNumber) {
        for (int i = 2; i <= Math.sqrt(maxNumber); i++) {
            for (int j = 2; i * j <= maxNumber; j++) {
                isNotPrime[i * j] = true;
            }
        }
    }

    private void createPossibleNumbers(String numbers, String current, Set<Integer> numberSet) {
        for (int i = 0; i < numbers.length(); i++) {
            String number = numbers.charAt(i) + current;
            numberSet.add(Integer.parseInt(number));
            createPossibleNumbers(numbers.substring(0, i) + numbers.substring(i + 1), number, numberSet);
        }
    }


}