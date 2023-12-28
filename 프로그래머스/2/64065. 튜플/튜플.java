import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
    public int[] solution(String s) {
        Pattern pattern = Pattern.compile("\\{[0-9,]+\\}");
        Matcher matcher = pattern.matcher(s.substring(1, s.length() - 1));

        List<String> tuples = new ArrayList<>();
        while (matcher.find()) {
            tuples.add(matcher.group());
        }

        tuples.sort(new SizeComparator());

        List<Integer> result = new ArrayList<>();
        for (String tuple : tuples) {
            StringTokenizer st = new StringTokenizer(tuple.substring(1, tuple.length() - 1), ",");
            while (st.hasMoreTokens()) {
                Integer number = Integer.parseInt(st.nextToken());
                if (!result.contains(number)) {
                    result.add(number);
                    break;
                }
            }
        }

        return result.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private static class SizeComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            return Integer.compare(s1.length(), s2.length());
        }
    }
}