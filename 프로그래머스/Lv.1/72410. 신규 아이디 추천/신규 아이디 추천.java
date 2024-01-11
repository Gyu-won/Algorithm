class Solution {
    private static final int MAX_LENGTH = 15;
    private static final int MIN_LENGTH = 3;

    public String solution(String id) {
        String answer = id.toLowerCase();
        answer = answer.replaceAll("[^a-z0-9-_.]", "");
        answer = answer.replaceAll("\\.{2,}", ".");
        answer = answer.replaceAll("^\\.|\\.$", "");
        answer = answer.isEmpty() ? "a" : answer;
        answer = answer.length() > MAX_LENGTH ? answer.substring(0, MAX_LENGTH) : answer;
        answer = answer.replaceAll("^\\.|\\.$", "");
        answer = checkMinLength(answer);

        return answer;
    }

    private String checkMinLength(String answer) {
        if (answer.length() < MIN_LENGTH) {
            answer = checkMinLength(answer + answer.charAt(answer.length() - 1));
        }
        return answer;
    }
}