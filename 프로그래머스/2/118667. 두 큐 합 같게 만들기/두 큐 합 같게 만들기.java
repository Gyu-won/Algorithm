import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int solution(int[] queue1, int[] queue2) {

        long queue1Sum = 0;
        long queue2Sum = 0;
        Deque<Integer> queueA = new ArrayDeque<>();
        Deque<Integer> queueB = new ArrayDeque<>();
        for (int i = 0; i < queue1.length; i++) {
            queue1Sum += queue1[i];
            queue2Sum += queue2[i];
            queueA.addLast(queue1[i]);
            queueB.addLast(queue2[i]);
        }

        long totalSum = queue1Sum + queue2Sum;
        if (totalSum % 2 == 1) {
            return -1;
        }

        int maximumCount = queue1.length * 4;
        int count = 0;
        long answerSum = totalSum / 2;
        while (queue1Sum != answerSum) {
            if (queue1Sum + queueB.getFirst() > answerSum) {
                int element = queueA.removeFirst();
                queue1Sum -= element;
                queueB.addLast(element);
            } else {
                int element = queueB.removeFirst();
                queue1Sum += element;
                queueA.addLast(element);
            }
            count++;

            if (queue1Sum == 0) {
                while (queue1Sum < answerSum) {
                    queue1Sum += queueB.removeFirst();
                    count++;
                }
                if (queue1Sum == answerSum) {
                    break;
                }
                return -1;
            }
            if (queue2Sum == 0) {
                while (queue1Sum > answerSum) {
                    queue1Sum -= queueA.removeFirst();
                    count++;
                }
                if (queue1Sum == answerSum) {
                    break;
                }
                return -1;
            }
            if (count == maximumCount) {
                return -1;
            }
        }
        return count;
    }

    // 합이 홀수인 경우 무조건 -1 리턴
    // 합을 구하고 2로 나누어서 목표 값을 지정
    // queue2 첫번쨰 원소를 queue1 합에 더했을 때 목표값보다 작거나 같으면 queue pop 후 insert
    // `` 목표값보다 크면 queue1을 pop 후 insert
    // 목표값과 같으면 종료
    // queue전체 길이만큼 반복하면 종료
}