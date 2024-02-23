import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int[] order;
	static boolean[] visited;
	static int N;
	static int[][] innings;
	static int maxScore;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		innings = new int[N+1][10];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 9; j++) {
				innings[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		order = new int[10];
		visited = new boolean[10];
		order[4] = 1;
		maxScore = Integer.MIN_VALUE;
		dfs(1);
		
		System.out.println(maxScore);
	}

	private static void dfs(int depth) {
		if (depth == 4) {
//			order[4] = 1;
			dfs(depth+1);
			return;
		}
		if (depth == 10) {
			game();
			return;
		}
		
		for (int i = 2; i <= 9; i++) {
			if (!visited[i]) {
				visited[i] = true;
				order[depth] = i;
				dfs(depth+1);
				visited[i] = false;
			}
		}
	}

	private static void game() {
		int index = 0;
		int base = 0;
		int score = 0;
		for (int i = 1; i <= N; i++) {
			base = 0;
			int outcount = 0;
			while(outcount < 3) {
				int playerNumber = order[(index % 9) + 1];
				int heatNumber = innings[i][playerNumber];
				switch (heatNumber) {
				case 0:
					outcount++;
					break;
				case 1:
					base <<= 1;
					base += 1;
					if ((base & (1 << 3)) != 0) {
						score++;
					}
					break;
				case 2:
					base <<= 1;
					base += 1;
					base <<= 1;
					for (int j = 0; j < 2; j++) {
						if ((base & (1 << 3 + j)) != 0) {
							score++;
						}
					}
					break;
				case 3:
					base <<= 1;
					base += 1;
					base <<= 2;
					for (int j = 0; j < 3; j++) {
						if ((base & (1 << 3 + j)) != 0) {
							score++;
						}
					}
					break;
				case 4:
					base <<= 1;
					base += 1;
					base <<= 3;
					for (int j = 0; j < 4; j++) {
						if ((base & (1 << 3 + j)) != 0) {
							score++;
						}
					}
					break;
				}
				
				index++;
			}
		}
		
		maxScore = Math.max(maxScore, score);
	}
}
