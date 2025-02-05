import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int[] satisfy = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			satisfy[i] = Integer.parseInt(st.nextToken());
		}

		int maxEnergy = findMaxEnergy(n, k, satisfy);
		System.out.println(maxEnergy);
	}

	private static int findMaxEnergy(int n, int k, int[] satisfy) {
		Deque<EatingPlan> eatingPlans = new ArrayDeque<>();
		eatingPlans.addLast(new EatingPlan(0, 0, 0));

		for (int i = 0; i < n; i++) {
			while (!eatingPlans.isEmpty() && eatingPlans.getFirst().level == i) {
				EatingPlan eatingPlan = eatingPlans.removeFirst();
				int current = eatingPlan.current;
				int total = eatingPlan.total;
				int level = eatingPlan.level;

				if (current == 0) {
					eatingPlans.addLast(new EatingPlan(current, total, level + 1));
				}
				current += satisfy[i];
				if (current >= k) {
					total += current - k;
					current = 0;
				}
				eatingPlans.addLast(new EatingPlan(current, total, level + 1));
			}
		}

		int maxEnergy = 0;
		while (!eatingPlans.isEmpty()) {
			int energy = eatingPlans.removeFirst().total;
			maxEnergy = Math.max(energy, maxEnergy);
		}
		return maxEnergy;
	}

	static class EatingPlan {
		private final int current;
		private final int total;
		private final int level;

		EatingPlan(int current, int total, int level) {
			this.current = current;
			this.total = total;
			this.level = level;
		}
	}
}
