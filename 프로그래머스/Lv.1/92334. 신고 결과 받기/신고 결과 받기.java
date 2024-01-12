// 신고당한사람: 신고한사람을 유지
// 신고당한 사람의 횟수가 k
// 유저: 메일 받은 횟수
// 유저 순서대로 리턴

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

class Solution {
    public int[] solution(String[] idList, String[] reports, int k) {

        Map<String, Set<String>> reportMap = new HashMap<>();
        updateReportResult(reports, reportMap);

        Map<String, Integer> reportNumberMap = updateReportNumberMap(k, reportMap);

        List<Integer> answer = calculateReportSuccessNumber(idList, reportNumberMap);

        return answer.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private Map<String, Integer> updateReportNumberMap(int k, Map<String, Set<String>> reportMap) {
        Map<String, Integer> reportNumberMap = new HashMap<>();
        for (String reportedPerson : reportMap.keySet()) {
            Set<String> reporters = reportMap.get(reportedPerson);
            if (reporters.size() >= k) {
                for (String reporter : reporters) {
                    reportNumberMap.put(reporter, reportNumberMap.getOrDefault(reporter, 0) + 1);
                }
            }
        }
        return reportNumberMap;
    }

    private static List<Integer> calculateReportSuccessNumber(String[] idList, Map<String, Integer> reportNumberMap) {
        List<Integer> answer = new ArrayList<>();
        for (String id : idList) {
            answer.add(reportNumberMap.getOrDefault(id, 0));
        }
        return answer;
    }

    private static void updateReportResult(String[] reports, Map<String, Set<String>> reportMap) {
        for (String report : reports) {
            StringTokenizer st = new StringTokenizer(report, " ");
            String reporter = st.nextToken();
            String reportedPerson = st.nextToken();

            Set<String> reportedList = new HashSet<>(reportMap.getOrDefault(reportedPerson, Collections.emptySet()));
            reportedList.add(reporter);
            reportMap.put(reportedPerson, reportedList);
        }
    }
}