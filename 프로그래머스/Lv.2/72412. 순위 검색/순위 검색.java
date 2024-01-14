import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int[] solution(String[] infos, String[] queries) {
        // info가 저장된 모든 경우의 Map을 만든다
        Map<String, ArrayList<Integer>> conditionScoreMap = new HashMap<>();

        for (String info : infos) {
            String[] data = info.split(" ");
            String[] languages = new String[]{data[0], "-"};
            String[] jobs = new String[]{data[1], "-"};
            String[] careers = new String[]{data[2], "-"};
            String[] foods = new String[]{data[3], "-"};
            for (String language : languages) {
                for (String job : jobs) {
                    for (String career : careers) {
                        for (String food : foods) {
                            String key = language + job + career + food;
                            ArrayList<Integer> values = conditionScoreMap.getOrDefault(key, new ArrayList<>());
                            values.add(Integer.parseInt(data[4]));
                            conditionScoreMap.put(key, values);
                        }
                    }
                }
            }
        }

        // 점수들을 오름차순 정렬한다.
        for (ArrayList<Integer> scores : conditionScoreMap.values()) {
            Collections.sort(scores);
        }

        // 쿼리에 맞는 지원자를 조회한다.
        int[] answer = new int[queries.length];
        int i = 0;
        for (String query : queries) {
            String[] data = query.split(" and ");
            String[] foodAndScore = data[3].split(" ");
            int score = Integer.parseInt(foodAndScore[1]);
            String key = data[0] + data[1] + data[2] + foodAndScore[0];

            List<Integer> scores = conditionScoreMap.getOrDefault(key, new ArrayList<>());

            // 이분탐색으로 점수를 비교하여 answer를 가져온다.
            int low = 0;
            int high = scores.size();
            while (low < high) {
                int mid = (low + high) / 2;
                if (scores.get(mid) >= score) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }
            answer[i++] = scores.size() - low;
        }
        return answer;
    }
}