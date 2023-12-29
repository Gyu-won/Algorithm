class Solution {
    public int solution(int n, int k) {
        int answer = 0;

        // k 진수로 만들기
        StringBuilder KNumber = new StringBuilder();
        while (n > 0) {
            KNumber.append(n % k);
            n = n / k;
        }

        String[] numbers = KNumber.reverse().toString().split("0+");

        for (String number : numbers) {
            if (isPrime(Long.valueOf(number))) {
                answer += 1;
            }
        }

        return answer;
    }

    private boolean isPrime(Long number) {
        if (number < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}