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
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());

        // Point들을 저장한다.
        StringTokenizer st;
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            points.add(new Point(st.nextToken(), st.nextToken()));
        }

        //정렬한다.
        points.sort(new Comparator<Point>() {
            @Override
            public int compare(Point point1, Point point2) {
                if (point1.getX() > point2.getX()) {
                    return 1;
                }
                if (point1.getX() == point2.getX()) {
                    if (point1.getY() > point2.getY()) {
                        return 1;
                    }
                    return -1;
                }
                return -1;
            }
        });

        //결과를 출력한다.
        StringBuilder sb = new StringBuilder();
        points.forEach(point -> sb.append(point.getX() + " " + point.getY() + "\n"));
        System.out.println(sb);
    }

    static class Point {
        int x;
        int y;

        public Point(String x, String y) {
            this.x = Integer.parseInt(x);
            this.y = Integer.parseInt(y);
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}