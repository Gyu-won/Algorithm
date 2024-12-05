import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());

		StringBuilder result = new StringBuilder();
		for (int i = 0; i < t; i++) {
			int numberOfEmployees = recruitNewEmployees(br);
			result.append(numberOfEmployees);
			result.append("\n");
		}

		System.out.println(result.toString().trim());
	}

	private static int recruitNewEmployees(BufferedReader br) throws IOException {
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st;

		List<Applicant> applicantList = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int cvRank = Integer.parseInt(st.nextToken());
			int interviewRank = Integer.parseInt(st.nextToken());
			applicantList.add(new Applicant(cvRank, interviewRank));
		}

		orderByCvRank(applicantList);
		int recruitNumber = countRecruitableEmployees(applicantList);
		return recruitNumber;
	}

	private static void orderByCvRank(List<Applicant> applicantList) {
		applicantList.sort(new ApplicantComparator());
	}

	private static int countRecruitableEmployees(List<Applicant> applicantList) {
		int numberOfRecruit = 0;
		int minInterviewRank = Integer.MAX_VALUE;

		for (Applicant applicant : applicantList) {
			int interviewRank = applicant.interviewRank;
			if (interviewRank < minInterviewRank) {
				numberOfRecruit++;
				minInterviewRank = interviewRank;
			}
		}
		return numberOfRecruit;
	}

	private static class Applicant {
		private final int cvRank;
		private final int interviewRank;

		Applicant(int cvRank, int interviewRank) {
			this.cvRank = cvRank;
			this.interviewRank = interviewRank;
		}
	}

	private static class ApplicantComparator implements Comparator<Applicant> {
		@Override
		public int compare(Applicant applicant1, Applicant applicant2) {
			return applicant1.cvRank - applicant2.cvRank;
		}
	}
}

// 1. 서류 순으로 정렬 O(nlogn)
// 2. 자기보다 위에 있는 애들보다 등수가 크면 됨 O(n)
// 최솟값 들고 있으면서 그거보다 작은지 확인

// O(nlogn + n)
