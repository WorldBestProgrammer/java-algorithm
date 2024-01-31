import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		char[] sequence1 = br.readLine().toCharArray();
		char[] sequence2 = br.readLine().toCharArray();
		int N = sequence1.length;
		int M = sequence2.length;
		
		int[][] dp = new int[N + 1][M + 1];
		
		int lcs = findLCSLength(sequence1, sequence2, N, M, dp);
		int x = N;
		int y = M;
//		display(dp);
		StringBuilder sb = new StringBuilder();
		
		while (x>0 && y>0) {
			if (x == 0 || y == 0) {
				break;
			}
			
//				if (dp[x-1][y] > dp[x][y-1]) {
//					x -= 1;
//				} else if (dp[x-1][y] < dp[x][y-1]) {
//					y -= 1;
//				} else if (dp[x-1][y] == dp[x][y-1] && sequence1[x-1] == sequence2[y-1]) {
//					sb.append(sequence1[x-1]);
//					x -= 1;
//					y -= 1;
//				} else {
//					x -= 1;
//				}
			
			if (dp[x][y] == dp[x-1][y]) {
				x--;
			} else if (dp[x][y] == dp[x][y-1]) {
				y--;
			} else {
				sb.append(sequence1[x-1]);
				x--;
				y--;
			}
			}
			
		System.out.println(lcs);
		System.out.print(sb.reverse().toString());
//		System.out.println(lcs);
//		bw.write(lcs + '0');
//		bw.newLine();
//		if (lcs > 0) {
//		bw.write(sb.reverse().toString());
//		}
//		bw.close();
	}
	private static void display(int[][] graph) {
		for (int i = 0 ; i < graph.length; i++) {
			for (int j = 0; j < graph[0].length; j++) {
				System.out.print(graph[i][j] + " ");
			}
			System.out.println();
		}
		
	}
	private static int findLCSLength(char[] sequence1, char[] sequence2, int N, int M, int[][] dp) {
		for (int i = 1; i <= N; i++) {	
			for (int j = 1; j <= M; j++) {
				if (sequence1[i-1] == sequence2[j-1]) {
					dp[i][j] = dp[i-1][j-1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		
		return dp[N][M];
	}
}