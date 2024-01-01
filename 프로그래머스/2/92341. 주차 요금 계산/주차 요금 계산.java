import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        List<Integer> answer = new ArrayList<>();
        int basicTime = fees[0];
        int basicFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];

        // 주차 시간 계산
        Map<String, LocalTime> entranceMap = new HashMap<>();
        Map<String, Integer> parkingTimeMap = new HashMap<>();
        for (String record : records) {
            StringTokenizer recordTokenizer = new StringTokenizer(record, " ");
            StringTokenizer timeTokenizer = new StringTokenizer(recordTokenizer.nextToken(), ":");

            int hour = Integer.parseInt(timeTokenizer.nextToken());
            int minute = Integer.parseInt(timeTokenizer.nextToken());
            LocalTime time = LocalTime.of(hour, minute);
            String carNumber = recordTokenizer.nextToken();

            if (entranceMap.containsKey(carNumber)) {
                Duration duration = Duration.between(entranceMap.remove(carNumber), time);
                parkingTimeMap.put(carNumber, parkingTimeMap.getOrDefault(carNumber, 0) + (int) duration.toMinutes());
            } else {
                entranceMap.put(carNumber, time);
            }
        }
        LocalTime closeTime = LocalTime.of(23, 59);
        for (Entry<String, LocalTime> record : entranceMap.entrySet()) {
            Duration duration = Duration.between(record.getValue(), closeTime);
            parkingTimeMap.put(record.getKey(),
                    parkingTimeMap.getOrDefault(record.getKey(), 0) + (int) duration.toMinutes());
        }
        System.out.println(parkingTimeMap.toString());

        // 차량 번호대로 정렬
        List<String> sortedCarNumbers = new ArrayList<>(parkingTimeMap.keySet());
        Collections.sort(sortedCarNumbers);

        // 주차 요금 계산
        for (String carNumber : sortedCarNumbers) {
            int parkingTime = parkingTimeMap.get(carNumber);
            int additionalFee = 0;
            if (parkingTime > basicTime) {
                additionalFee = (int) Math.ceil((double) (parkingTime - basicTime) / unitTime) * unitFee;
            }
            answer.add(basicFee + additionalFee);
        }

        return answer.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}