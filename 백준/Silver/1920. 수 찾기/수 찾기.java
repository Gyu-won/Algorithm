import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws Exception {
		StringBuilder result = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		List<Integer> searchList = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int number = Integer.parseInt(st.nextToken());
			searchList.add(number);
		}

		Collections.sort(searchList);

		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			int number = Integer.parseInt(st.nextToken());
			result.append(binarySearch(number, searchList));
			result.append("\n");
		}

		System.out.println(result.toString().trim());
	}

	private static int binarySearch(int number, List<Integer> searchList) {

		int mid, start = 0, end = searchList.size() - 1;
		while (start <= end) {
			mid = (start + end) / 2;
			int currentNumber = searchList.get(mid);

			if (number < currentNumber) {
				end = mid - 1;
			}else if (number > currentNumber){
				start = mid + 1;
			} else{
				return 1;
			}
		}
		return 0;
	}
}

// searchList 정렬: arrayList
// 하나씩 입력 받으면서 binarySearch

// 완탐
// contains 사용 불가능
// 이분탐색 -> O(nlogn)
