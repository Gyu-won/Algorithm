import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main extends Object {

    static class Student {
        private int number;
        private int numberOfRecommend;
        private int sequence;

        private Student(int number, int sequence) {
            this.number = number;
            this.numberOfRecommend = 0;
            this.sequence = sequence;
        }

        public void recommended() {
            numberOfRecommend++;
        }
    }

    public static void main(String[] args) throws IOException {
        // numberOfFrame을 입력받는다. (int)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numberOfFrame = Integer.parseInt(br.readLine());

        // recommend를 입력받는다. (int)
        int recommend = Integer.parseInt(br.readLine());

        // 추천학생을 하나씩 입력받으면서 Student를 만들고, candidateHeap에 넣는다.
        Map<Integer, Student> studentByNumber = new HashMap<>();
        PriorityQueue<Student> candidateHeap = new PriorityQueue<>(new StudentComparator());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < recommend; i++) {
            int number = Integer.parseInt(st.nextToken());
            // 포함되어 있음 -> recommend 값바꾸고 힙에서 삭제 후 다시 add
            if (studentByNumber.containsKey(number)) {
                Student student = studentByNumber.get(number);
                student.recommended();
                candidateHeap.remove(student);
                candidateHeap.offer(student);
            } else {
                // 사이즈 같고 포함 안됨 -> delete 후 add
                if (candidateHeap.size() == numberOfFrame) {
                    Student removedStudent = candidateHeap.poll();
                    studentByNumber.remove(removedStudent.number);
                }
                // 사이즈 작고 포함 안됨 -> 그냥 add만
                Student student = new Student(number, i);
                student.recommended();
                studentByNumber.put(number, student);
                candidateHeap.offer(student);
            }
        }

        // map에 남아있는 학생들의 number를 가져온다.
        List<Integer> candatates = new ArrayList<>(studentByNumber.keySet());

        // 오름차순 정렬하여 결과를 출력한다.
        Collections.sort(candatates);

        StringBuilder answer = new StringBuilder();
        for (int candidate : candatates) {
            answer.append(candidate);
            answer.append(" ");
        }
        System.out.println(answer.toString().trim());
    }

    private static class StudentComparator implements Comparator<Student> {
        @Override
        public int compare(Student s1, Student s2) {
            // 추천수로 오름차순 정렬, 같으면 sequence로 오륾차순 정렬
            if (s1.numberOfRecommend == s2.numberOfRecommend) {
                return s1.sequence - s2.sequence;
            }
            return s1.numberOfRecommend - s2.numberOfRecommend;
        }
    }
}

// 알고리즘: 정렬 이용

// 시간복잡도: O(r *  n * nlogn)

// 정수 범위
