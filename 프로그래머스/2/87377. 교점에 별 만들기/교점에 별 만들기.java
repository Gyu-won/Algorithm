import java.util.*;

class Solution {
    
    private static long maxX = Long.MIN_VALUE;
    private static long maxY = Long.MIN_VALUE;
    private static long minX = Long.MAX_VALUE;
    private static long minY = Long.MAX_VALUE;
    
    // line 1000 이하
    public String[] solution(int[][] lines) {
        // 교점 구하기 (10^6)
        // 넣으면서 교점의 x, y 최대 최소 구하기
        Set<Point> points = new HashSet<>();
        for (int i = 0; i < lines.length - 1; i++) {
            for (int j = i + 1; j < lines.length ; j++) {
                long divisor = ((long) lines[i][0]) * lines[j][1] - ((long)lines[i][1]) * lines[j][0];
                if (divisor == 0) {
                    continue;
                }
                
                long bfed = ((long) lines[i][1] * lines[j][2]) - ((long)lines[i][2] * lines[j][1]);
                if (bfed % divisor != 0) {
                    continue;
                }
                long ecaf = ((long) lines[i][2] * lines[j][0]) - ((long)lines[i][0] * lines[j][2]);
                if (ecaf % divisor != 0) {
                    continue;
                }
                
                long x = bfed / divisor;
                long y = ecaf / divisor;
                updateMinMax(x, y);
                points.add(new Point(x, y));
            }
        }
        
        // char[][] 배열로 나타내기 O(10^6)
        int ySize = (int) (maxY - minY + 1);
        int xSize = (int) (maxX - minX + 1);
        boolean[][] arr = new boolean[ySize][xSize];
        for (Point point: points) {
            arr[(int) (point.y - minY)][(int) (point.x - minX)] = true;
        }
        
        // String[]으로 나타내기 O(10^6)        
        String[] answer = new String[ySize];
        for (int i = 0; i < ySize; i++) {
            StringBuilder s = new StringBuilder();
            for (int j = 0; j < xSize; j++) {
                if (arr[i][j]) {
                    s.append('*');
                } else{
                    s.append('.');
                }
            }
            answer[ySize - 1 - i] = s.toString();
        }
        return answer;
    }
                                   
    private void updateMinMax(long x, long y) {
        maxX = Math.max(maxX, x);
        maxY = Math.max(maxY, y);
        minX = Math.min(minX, x);
        minY = Math.min(minY, y);
    }
    
    private class Point {
        private final long x;
        private final long y;
        
        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            Point point = (Point) obj;
            return this.x == point.x && this.y == point.y;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
