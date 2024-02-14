import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("C:\\SSAFY\\Java\\workspace\\02.Algorithm\\my-algorithm\\src\\boj\\solution1074\\input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		int T = Integer.parseInt(br.readLine());
//		for (int testCase = 1; testCase <= T; testCase++) {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());		
		
//		for (int i = 0; i < Math.pow(2, N); i++) {
//			for (int j = 0; j < Math.pow(2, N); j++) {
//				System.out.print(findVisitTime(N, i, j) + " ");
//			}
//			System.out.println();
//		}
//		System.out.println(testCase + " " + findVisitTime(N, r, c));
		System.out.println(findVisitTime(N, r, c));
//		}
	}

	private static int findVisitTime(int n, int r, int c) {
		if (n == 0) {
			return 0;
		}
		
		int areaNumber = findWhichArea(n, r, c);
		int result = (areaNumber - 1) * ((int) (Math.pow(2, n) / 4 * Math.pow(2, n)));
		int N = (int) Math.pow(2, n);
		if (areaNumber == 2) {
			c -= N / 2;
		} else if (areaNumber == 3) {
			r -= N / 2;
		} else if (areaNumber == 4) {
			r -= N / 2;
			c -= N / 2;
		}
		result += findVisitTime(n - 1, r, c);
		return result;
	}

	private static int findWhichArea(int n, int r, int c) {
		int N = (int) Math.pow(2, n);
		if (0 <= r && r <= (N-1) / 2 && 0 <= c && c <= (N-1) / 2) {
			return 1;
		}
		if (0 <= r && r <= (N-1) / 2 && (N+1) / 2 <= c && c <= (N-1)) {
			return 2;
		}
		if ((N + 1) / 2 <= r && r <= N-1 && 0 <= c && c <= (N-1) / 2) {
			return 3;
		}
		if ((N + 1) / 2 <= r && r <= N-1 && (N+1) / 2 <= c && c <= (N-1)) {
			return 4;
		}
		return 0;
	}
}
