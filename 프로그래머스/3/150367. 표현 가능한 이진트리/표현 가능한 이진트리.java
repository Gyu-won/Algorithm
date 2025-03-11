class Solution {
    // numbers <= 10^4
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        
        // number 개수만큼 반복: O(10^4)
        for (int i = 0; i < numbers.length; i++) {
            // 숫자를 트리로 완전 이진 트리로 표현 O(6)
            String binary = convertToBinary(numbers[i]);
        
            // 트리의 부모가 0이면 자식은 무조건 0인지 확인: O(128)
            if (isValid(binary, 0, binary.length() - 1)) {
                answer[i] = 1;
            }
        }
        return answer;
    }
    
    private boolean isValid(String binary, int start, int end) {
        if (start >= end) {
            return true;
        }
        
        int mid = (start + end) / 2;
        
        if (binary.charAt(mid) == '0') {
            return isZero(binary, start, mid - 1) && isZero(binary, mid + 1, end);
        }
        
        return isValid(binary, start, mid - 1) && isValid(binary, mid + 1, end);       
    }
    
    private boolean isZero(String binary, int start, int end) { 
        if (start >= end){
            return binary.charAt(start) == '0';  
        }
        
        int mid = (start + end) / 2;
        
        if (binary.charAt(mid) == '1') {
            return false;
        }
        
        return isZero(binary, start, mid - 1) && isZero(binary, mid + 1, end);
    }
    
    private String convertToBinary(Long number) {
        long nodeNum = 2;
        int size = 1;
        int len = 1;
        while(nodeNum <= number) {
            if (len == 31) {
                len = 63;
                break;
            }
            size *= 2;
            nodeNum = nodeNum << size;
            len += size;
        }
        
        String binary = Long.toBinaryString(number);
        return "0".repeat(len - binary.length()) + binary;
    }
}
