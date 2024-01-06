import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
    public String[] solution(String[] records) {
        List<String> messages = new ArrayList<>();
        Map<String, String> nickNameMap = new HashMap<>();

        for (String record : records) {
            StringTokenizer st = new StringTokenizer(record, " ");
            String command = st.nextToken();
            String userId = st.nextToken();

            if (command.equals("Enter")) {
                String nickName = st.nextToken();
                messages.add(String.format("%s님이 들어왔습니다.", userId));
                nickNameMap.put(userId, nickName);
            } else if (command.equals("Leave")) {
                messages.add(String.format("%s님이 나갔습니다.", userId));
            } else if (command.equals("Change")) {
                String nickName = st.nextToken();
                nickNameMap.put(userId, nickName);
            }
        }

        for (int index = 0; index < messages.size(); index++) {
            String message = messages.get(index);
            Pattern pattern = Pattern.compile("[a-zA-Z0-9]+");
            Matcher matcher = pattern.matcher(message);
            if (matcher.find()) {
                String userId = matcher.group();
                messages.set(index, message.replace(userId, nickNameMap.get(userId)));
            }
        }

        return messages.toArray(String[]::new);
    }

    // enter, leave -> list에 userId 님이 enter/leave
    // change -> map nickname을 바꾸기
    // 최종결과는 userId를 nickName으로 다 바꾸고 출력
}