import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int numberOfTrucks = Integer.parseInt(st.nextToken());
		int lengthOfBridge = Integer.parseInt(st.nextToken());
		int maximumBridgeLoad = Integer.parseInt(st.nextToken());

		Deque<Integer> truckWeights = new ArrayDeque<>();
		st = new StringTokenizer(br.readLine());
		for (int truckNumber = 0; truckNumber < numberOfTrucks; truckNumber++) {
			truckWeights.addLast(Integer.parseInt(st.nextToken()));
		}

		System.out.println(measureCrossTime(lengthOfBridge, maximumBridgeLoad, truckWeights));
	}

	private static int measureCrossTime(int lengthOfBridge, int maximumBridgeLoad, Deque<Integer> truckWeights) {
		int crossTime = 0, bridgeLoad = 0;

		Deque<Integer> bridge = new ArrayDeque<>();
		for (int length = 0; length < lengthOfBridge; length++) {
			bridge.addLast(0);
		}

		while (!truckWeights.isEmpty()) {
			crossTime++;

			bridgeLoad -= bridge.removeFirst();
			int firstTruckWeight = truckWeights.getFirst();
			if (bridgeLoad + firstTruckWeight > maximumBridgeLoad) {
				bridge.addLast(0);
			} else {
				bridge.addLast(truckWeights.removeFirst());
				bridgeLoad += firstTruckWeight;
			}
		}
		return crossTime + lengthOfBridge;
	}
}

// n개의 트럭, w다리길이, L 무게
// 칸이 있어서 투포인터를 떠올렸지만, 칸의 길이가 일정하지 않을 수 있다.
// 뒤에 하나 들어가고, 앞에꺼 하나 나오기 떄문에 queue 자료구조
