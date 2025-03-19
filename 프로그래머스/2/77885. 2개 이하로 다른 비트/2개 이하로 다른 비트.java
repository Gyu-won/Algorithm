class Solution {
    // 10^5개, 숫자범위: 2^50
    public long[] solution(long[] numbers) {
        // 이진수 변환
        long[] answer = new long[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            String binaryNumber = Long.toString(numbers[i], 2);
            answer[i] = getAnswer(binaryNumber);
        }
        return answer;
    }
    
    private Long getAnswer(String binary) {
        // 뒤에서 부터 보면서 0이 있으면 그걸 1로 변환
        int len = binary.length();
        int i = 0;
        for (; i < len; i++) {
            if (binary.charAt(len - i - 1) == '0') {
                if (i == 0) {
                    return Long.parseLong(binary, 2) + 1;
                }
                break;
            }
        }
        // 0이 없으면 제일 앞에껄 1로 바꾸고 그 밑에껄 0으로 변환
        return Long.parseLong(binary, 2) + (1L << (i - 1));
    }
}

