import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
    public String[] solution(String[] files) {
        List<FileName> fileNames = new ArrayList<>();

        for (int index = 0; index < files.length; index++) {
            fileNames.add(new FileName(index, files[index]));
        }

        Collections.sort(fileNames, new FileNameComparator());

        return fileNames.stream()
                .map(FileName::toString)
                .toArray(String[]::new);
    }

    private class FileName {
        private int index;
        private String head;
        private String number;
        private String tail;

        public FileName(int index, String file) {
            Pattern pattern = Pattern.compile("[\\d]{1,5}");
            Matcher matcher = pattern.matcher(file);
            if (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();
                this.index = index;
                head = file.substring(0, start);
                number = file.substring(start, end);
                tail = file.substring(end);
            }
        }

        @Override
        public String toString() {
            return head + number + tail;
        }
    }

    private class FileNameComparator implements Comparator<FileName> {
        @Override
        public int compare(FileName fileName1, FileName fileName2) {
            String head1 = fileName1.head.toUpperCase();
            String head2 = fileName2.head.toUpperCase();
            int compareFlag = head1.compareTo(head2);
            if (compareFlag == 0) {
                int number1 = Integer.parseInt(fileName1.number);
                int number2 = Integer.parseInt(fileName2.number);
                if (number1 - number2 == 0) {
                    return fileName1.index - fileName2.index;
                }
                return number1 - number2;
            }
            return compareFlag;
        }
    }

    // head, number, tail로 나누기
    // 정렬
    // 결과 출력
}