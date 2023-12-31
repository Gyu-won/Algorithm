class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder answer = new StringBuilder();

        int lastNumber = m * (t - 1) + p;
        String totalString = "";
        for (int number = 0; ; number++) {
            totalString += Integer.toString(number, n).toUpperCase();
            if (totalString.length() >= lastNumber) {
                break;
            }
        }

        for (int i = p - 1; i < lastNumber; i += m) {
            answer.append(totalString.charAt(i));
        }

        System.out.println(answer.toString());

        return answer.toString();
    }
}