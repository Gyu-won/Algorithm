
import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] solution(int[] answers) {
        // 1번, 2번, 3번 수포자가 구했을 답을 다 구하기
        int numberOfQuestion = answers.length;
        int[] answerByOne = new int[numberOfQuestion];
        int[] answerByTwo = new int[numberOfQuestion];
        int[] answerByThree = new int[numberOfQuestion];
        for (int i = 0; i < answerByOne.length; i++) {
            answerByOne[i] = (i % 5) + 1;
            answerByTwo[i] = calculateTwoAnswer(i);
            answerByThree[i] = calculateThreeAnswer(i);
        }

        // answer와 비교하기
        int[] numberOfCorrects = new int[3];
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == answerByOne[i]) {
                numberOfCorrects[0]++;
            }
            if (answers[i] == answerByTwo[i]) {
                numberOfCorrects[1]++;
            }
            if (answers[i] == answerByThree[i]) {
                numberOfCorrects[2]++;
            }
        }

        // 최대 값을 구하기
        int maxNumberOfCorrect = 0;
        for (int numberOfCorrect : numberOfCorrects) {
            if (numberOfCorrect > maxNumberOfCorrect) {
                maxNumberOfCorrect = numberOfCorrect;
            }
        }

        // 최대값인 사람을 순서대로 더하기
        List<Integer> mostCorrectPersons = new ArrayList<>();
        for (int i = 0; i < numberOfCorrects.length; i++) {
            if (numberOfCorrects[i] == maxNumberOfCorrect) {
                mostCorrectPersons.add(i + 1);
            }
        }
        return mostCorrectPersons.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private int calculateThreeAnswer(int i) {
        int k = i % 10;
        if (k < 2) {
            return 3;
        } else if (k < 4) {
            return 1;
        } else if (k < 6) {
            return 2;
        } else if (k < 8) {
            return 4;
        }
        return 5;
    }

    private int calculateTwoAnswer(int i) {
        if (i % 2 == 0) {
            return 2;
        }
        if (i % 8 == 1) {
            return 1;
        }
        return (i % 8 + 1) / 2 + 1;
    }
}