import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        Deque<String> cache = new ArrayDeque<>();

        if (cacheSize == 0) {
            return cities.length * 5;
        }

        for (String city : cities) {
            String cityName = city.toUpperCase();
            if (cache.contains(cityName)) {
                answer += 1;
                cache.remove(cityName);
                cache.addLast(cityName);
                continue;
            }
            if (cache.size() == cacheSize) {
                cache.removeFirst();
            }
            cache.addLast(cityName);
            answer += 5;
        }
        return answer;
    }
}