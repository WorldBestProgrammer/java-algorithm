import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] order;
	static boolean[] visited;
	static int N;
	static int[][] innings;
	static int maxScore;
	static int[] nps;
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
		nps = new int[] {2, 3, 4, 5, 6, 7, 8, 9};
		do {
//			for (int i = 0; i < 3; i++) {
//				order[i+1] = nps[i];
//			}
//			for (int i = 3; i < 8; i++) {
//				order[i+2] = nps[i];
//			}
			game();
		} while(np());
//		dfs(1);
		
		System.out.println(maxScore);
	}

	private static void dfs(int depth) {
		if (depth == 4) {
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
	
	private static boolean np() {
		int i = 7;
		while (i > 0 && nps[i-1] >= nps[i]) {
			--i;
		}
		if (i == 0) {
			return false;
		}
		int j = 7;
		while (nps[i-1] >= nps[j]) {
			--j;
		}
		
		int temp = nps[i-1];
		nps[i-1] = nps[j];
		nps[j] = temp;
		
		for (int k = 0; k < (7 - i + 1) / 2 ; k++) {
			temp = nps[i+k];
			nps[i+k] = nps[7 - k];
			nps[7 - k] = temp;
		}
//		Arrays.sort(nps, i, 8);
		return true;
	}

	private static void game() {
		int index = 0;
		int base = 0;
		int score = 0;
		for (int i = 1; i <= N; i++) {
			base = 0;
			int outcount = 0;
			while(outcount < 3) {
				int curIndex = index % 9;
//				int playerNumber = order[(index % 9) + 1];
				int playerNumber = 0;
				if (curIndex <= 2) {
					playerNumber = nps[curIndex];
				} else if (curIndex == 3) {
					playerNumber = 1;
				} else {
					playerNumber = nps[curIndex-1];
				}
				
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
