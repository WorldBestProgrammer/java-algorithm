import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int maxCount;
	static int minSum;
	static int accSum;
	static int[][] map;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static List<Point> cores;
	static int C;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			cores = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1 && 1<= i && i <= N-2 && 1<= j && j <= N-2) {
						cores.add(new Point(i, j));
					}
				}
			}
			
			maxCount = Integer.MIN_VALUE;
			minSum = Integer.MAX_VALUE;
			accSum = 0;
			C = cores.size();
			exhaustiveSearch(0, 0);
			sb.append("#").append(testCase).append(" ").append(minSum).append("\n");
		}
		System.out.println(sb);
		
	}

	private static void exhaustiveSearch(int depth, int count) {
		if (depth == C) {
			if (count > maxCount) {
				minSum = accSum;
				maxCount = count;
			} else if (count == maxCount) {
				minSum = Math.min(minSum, accSum);
			}
			return;
		}
		
		for (int i = 0; i < 5; i++) {
			int dir = i;
			int r = cores.get(depth).x;
			int c = cores.get(depth).y;
			if (i == 4) {
				exhaustiveSearch(depth + 1, count);
			} else if (validLine(r, c, dir)) {
				makeLine(r, c, dir);
				exhaustiveSearch(depth + 1, count + 1);	
				removeLine(r, c, dir);
			}
		}
	}

	private static int findSum() {
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 2) {
					count++;
				}
			}
		}
		return count;
	}

	private static void removeLine(int r, int c, int dir) {
		int nr = r;
		int nc = c;
		while (true) {
			nr += dr[dir];
			nc += dc[dir];
			if (!(0 <= nr && nr < N && 0 <= nc && nc < N)) {
				return;
			}
			map[nr][nc] = 0;
			accSum--;
		}		
	}

	private static boolean validLine(int r, int c, int dir) {
		int nr = r;
		int nc = c;
		while (true) {
			nr += dr[dir];
			nc += dc[dir];
			if (!(0 <= nr && nr < N && 0 <= nc && nc < N)) {
				return true;
			}
			if (map[nr][nc] == 1 || map[nr][nc] == 2) {
				return false;
			}
		}
	}

	private static void makeLine(int r, int c, int dir) {
		int nr = r;
		int nc = c;
		while (true) {
			nr += dr[dir];
			nc += dc[dir];
			if (!(0 <= nr && nr < N && 0 <= nc && nc < N)) {
				return;
			}
			accSum++;
			map[nr][nc] = 2;
		}
	}
}
