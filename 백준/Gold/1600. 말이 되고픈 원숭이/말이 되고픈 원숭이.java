import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static int[][][] dp;
	static int[] dr;
	static int[] dc;
	static int W;
	static int H;
	static int K;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[W][H];
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[j][i] = Integer.parseInt(st.nextToken());
				if (map[j][i] == 1) {
					map[j][i] = -1;
				} else {
					map[j][i] = Integer.MAX_VALUE;
				}
			}
		}
		
		dr = new int[] {-1, 1, 0, 0, 1, 1, 2, 2, -1, -1, -2, -2};
		dc = new int[] {0, 0, -1, 1, 2, -2, 1, -1, 2, -2, 1, -1};
		int result = bfs(new int[] {0, 0, 0, 0});
		System.out.println(result);
		
		
	}
	private static int bfs(int[] start) {
		Deque<int[]> deque = new ArrayDeque<>();
		deque.add(start);
		
		while (!deque.isEmpty()) {
			int[] cur = deque.poll();
			int r = cur[0];
			int c = cur[1];
			int jump = cur[2];
			int time = cur[3];
			if (r == W-1 && c == H-1) {
				return time;
			}
			for (int i = 0; i < 12; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if (0 <= nr && nr < W && 0 <= nc && nc < H && map[nr][nc] != -1) {
					if (i >= 4) {
						if (jump >= K) {
							continue;
						}
						if (jump + 1 < map[nr][nc]) {
							map[nr][nc] = jump + 1;
							deque.add(new int[] {nr, nc, jump+1, time+1});
						}
					} else {
						if (jump < map[nr][nc]) {
							map[nr][nc] = jump;
							deque.add(new int[] {nr, nc, jump, time+1});
		
						}
					}
				}
			}
		}
		return -1;
	}
}
