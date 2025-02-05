class Solution {
    public int[] solution(int n, long left, long right) {
        int difference = (int) (right - left);
        int[] answer = new int[difference + 1];
        int idx = 0;
        for (long i = left; i <= right; i++) {
            int row = (int) (i / (long) n);
            int col = (int) (i % (long) n);
            
            if (col <= row) {
                answer[idx] = row + 1;
            } else {
                answer[idx] = row + col - row + 1;
            }
            idx++;
        }
        return answer;
    }
}