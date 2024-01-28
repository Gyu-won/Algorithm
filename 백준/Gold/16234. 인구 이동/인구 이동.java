import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static ArrayList<Country>[][] connection;
    private static boolean[][] visited;
    private static List<Country> union;

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().solution());
    }

    public int solution() throws IOException {
        // n, l, r을 입력받는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        // int[][] population을 입력받는다.
        Country[][] countries = new Country[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                countries[i][j] = new Country(i, j, Integer.parseInt(st.nextToken()));
            }
        }

        // moveDay와, unionCount를 선언한다.
        int moveDay = 0;

        while (true) {
            int unionCount = 0;

            // 연결리스트를 만든다.
            connection = new ArrayList[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    connection[i][j] = new ArrayList<>();
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i < n - 1) {
                        int difference = Math.abs(countries[i][j].population - countries[i + 1][j].population);
                        if (difference >= l && difference <= r) {
                            unionCount++;
                            connection[i][j].add(countries[i + 1][j]);
                            connection[i + 1][j].add(countries[i][j]);
                        }
                    }
                    if (j < n - 1) {
                        int difference = Math.abs(countries[i][j].population - countries[i][j + 1].population);
                        if (difference >= l && difference <= r) {
                            unionCount++;
                            connection[i][j].add(countries[i][j + 1]);
                            connection[i][j + 1].add(countries[i][j]);
                        }
                    }
                }
            }

            if (unionCount == 0) {
                break;
            }

            // dfs를 구현한다.
            visited = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j]) {
                        union = new ArrayList<>();
                        union.add(countries[i][j]);
                        int population = dfs(i, j, countries[i][j].population);
                        int averagePopulation = population / union.size();
                        for (Country country : union) {
                            country.updatePopulation(averagePopulation);
                        }
                    }
                }
            }
            moveDay++;
        }

        // moveDay를 리턴한다.
        return moveDay;
    }

    private int dfs(int i, int j, int totalPopulation) {
        visited[i][j] = true;
        for (Country country : connection[i][j]) {
            if (!visited[country.x][country.y]) {
                union.add(country);
                totalPopulation = dfs(country.x, country.y, totalPopulation + country.population);
            }
        }
        return totalPopulation;
    }

    static class Country {
        private final int x;
        private final int y;
        private int population;

        private Country(int x, int y, int population) {
            this.x = x;
            this.y = y;
            this.population = population;
        }

        public void updatePopulation(int averagePopulation) {
            this.population = averagePopulation;
        }
    }
}

// 알고리즘: count를 구하기 위해서는 인구 수를 update 하는 횟수를 구해야한다.
// 인구수를 update 하기 위해서는 연합의 인구수와 연합 국가 수를 구하고, 해당 값들을 바꾸어줘야한다.
// 연합을 구하기 위한 연결 리스트를 만든다.ArrayList<Country>[][]
// 방문 시 List<Coutry>에 추가하고, dfs로 연결리스트의 합을 구하고, 개수를 구해서 값을 list의 인구를 변경해준다.

// 시간복잡도: O(N*N*N)

// 정수 범위