import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
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
        mergeSort(points, 0, N - 1);

        //결과를 출력한다.
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (Point point : points) {
            bw.write(Integer.toString(point.getX()) + " " + Integer.toString(point.getY()) + "\n");
        }
        bw.flush();
        bf.close();
        bw.close();
    }

    private static void mergeSort(List<Point> points, int first, int last) {
        if (first < last) {
            int mid = (first + last) / 2;

            mergeSort(points, first, mid);
            mergeSort(points, mid + 1, last);

            merge(points, first, mid, last);
        }

    }

    private static void merge(List<Point> points, int first, int mid, int last) {
        int leftFirstIndex = first;
        int rightFirstIndex = mid + 1;

        List<Point> sortedPoints = new ArrayList<>();

        while (leftFirstIndex <= mid && rightFirstIndex <= last) {
            Point leftPoint = points.get(leftFirstIndex);
            Point rightPoint = points.get(rightFirstIndex);
            if (leftPoint.getX() < rightPoint.getX()) {
                sortedPoints.add(leftPoint);
                leftFirstIndex++;
            } else if (leftPoint.getX() == rightPoint.getX()) {
                if (leftPoint.getY() <= rightPoint.getY()) {
                    sortedPoints.add(leftPoint);
                    leftFirstIndex++;
                } else {
                    sortedPoints.add(rightPoint);
                    rightFirstIndex++;
                }
            } else {
                sortedPoints.add(rightPoint);
                rightFirstIndex++;
            }
        }

        if (leftFirstIndex > mid) {
            sortedPoints.addAll(points.subList(rightFirstIndex, last + 1));
        } else {
            sortedPoints.addAll(points.subList(leftFirstIndex, mid + 1));
        }

        for (int i = first; i <= last; i++) {
            points.set(i, sortedPoints.get(i - first));
        }
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