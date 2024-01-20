class Solution {
    private static int answer = 0;
    private static int[] numberArray;
    private static int targetNumber;

    public int solution(int[] numbers, int target) {

        numberArray = numbers;
        targetNumber = target;
        findTarget(0, 0);

        return answer;
    }

    private void findTarget(int sum, int current) {
        if (current == numberArray.length) {
            if (targetNumber == sum) {
                answer++;
            }
            return;
        }
        findTarget(sum + numberArray[current], current + 1);
        findTarget(sum - numberArray[current], current + 1);
    }

    // 모든 노드 다 봐야함.
    // 재귀 로 구현하면 되겠네.
}