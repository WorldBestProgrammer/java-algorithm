import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] graph;
	static int[] dx = {-1, 0, 1};
	static int[] dy = {1, 1, 1};
	static int R;
	static int C;
	static int count = 0;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		graph = new int[R][C];
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				if (line.charAt(j) == 'x') {
					graph[i][j] = -1;
				} else {
					graph[i][j] = 0;
				}
			}
		}
		
		for (int i = 0; i < R; i++) {
			dfs(i, 0);
		}
		System.out.println(count);
//		dfs(0, 0);
//		System.out.println(count);
//		for (int i = 0; i < R; i++) {
//			for (int j = 0; j < C; j++) {
//				System.out.print(graph[i][j] + " ");
//			}
//			System.out.println();
//		}
	}

	private static boolean dfs(int row, int col) {
//		if (row == R - 1 && col == C - 1) {
//			return;
//		}
		if (col == C - 1) {
			count++;
//			dfs(row + 1, 0);
			return true;
		}
		for (int i = 0; i < 3; i++) {
			int nRow = row + dx[i];
			int nCol = col + dy[i];
			if (!(0<= nRow && nRow < R && 0<= nCol && nCol < C)) {
				continue;
			}
			if (graph[nRow][nCol] == 0) {
				graph[nRow][nCol] = 1;
				if (dfs(nRow, nCol)) {
					return true;
				}
//				graph[nRow][nCol] = 0;
			}
		}
		return false;
	}
}
