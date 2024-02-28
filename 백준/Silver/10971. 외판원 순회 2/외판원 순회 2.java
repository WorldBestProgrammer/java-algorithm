import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] perm;
	static int minCost = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		int[][] adjcentMap = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int cost = Integer.parseInt(st.nextToken());
				adjcentMap[i][j] = cost;
			}
		}
		
		for (int i = 0; i < N; i++) {
			perm = new int[N-1];
			int k = 0;
			for (int j = 0; j < N; j++) {
				if (i != j) {
					perm[k++] = j;
				}
			}
			out: do {
				int cur = i;
				int totalCost = 0;
				for (int j = 0; j < N-1; j++) {
					int cost = adjcentMap[cur][perm[j]];
					if (cost == 0) {
						continue out;
					}
					totalCost += cost;
					cur = perm[j];
				}
				int end = i;
				if (adjcentMap[cur][end] == 0) {
						continue out;
					}
				totalCost += adjcentMap[cur][end];
				minCost = Math.min(minCost, totalCost);
			} while (np());
		}
		System.out.println(minCost);
	}

	private static boolean np() {
		int i = N-2;
		while (i > 0 && perm[i-1] >= perm[i]) {
			--i;
		}
		if (i == 0) {
			return false;
		}
		int j = N-2;
		while (perm[i-1] >= perm[j]) {
			--j;
		}
		
		int temp = perm[i-1];
		perm[i-1] = perm[j];
		perm[j] = temp;
		
		Arrays.sort(perm, i, N-1);
		return true;
	}
}
