import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // N을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // N명의 나이와 이름을 입력받는다.
        List<Member> members = new ArrayList<>();
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            members.add(new Member(st.nextToken(), st.nextToken(), i));
        }

        // 나이순, 나이가 같으면 가입한 순으로 정렬한다.
        members.sort(new Comparator<Member>() {
            @Override
            public int compare(Member firstMember, Member secondMember) {
                if (firstMember.getAge() > secondMember.getAge()) {
                    return 1;
                }
                if (firstMember.getAge() == secondMember.getAge()) {
                    if (firstMember.getSequence() > secondMember.getSequence()) {
                        return 1;
                    }
                    return -1;
                }
                return -1;
            }
        });

        //출력한다.
        StringBuilder result = new StringBuilder();
        members.forEach(member ->
                result.append(String.format("%d %s\n", member.getAge(), member.getName())));
        System.out.println(result);
    }

    static class Member {
        int age;
        int sequence;
        String name;

        public Member(String age, String name, int sequence) {
            this.age = Integer.parseInt(age);
            this.name = name;
            this.sequence = sequence;
        }

        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }

        public int getSequence() {
            return sequence;
        }
    }
}