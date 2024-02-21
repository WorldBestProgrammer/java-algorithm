import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] map;
	static List<Node> cctvs;
	static int[][][] dxs;
	static int[][][] dys;
	static int N;
	static int M;
	static int minCount;
	static int targetDepth;
	static class Node {
		int x;
		int y;
		int direction;
		int number;
		public Node(int x, int y, int direction, int number) {
			super();
			this.x = x;
			this.y = y;
			this.direction = direction;
			this.number = number;
		}
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		dxs = new int[6][][];
		dys = new int[6][][];
		
		dxs[1] = new int[][]{{-1}, {1}, {0}, {0}};
		dys[1] = new int[][]{{0}, {0}, {-1}, {1}};

		dxs[2] = new int[][]{{-1, 1}, {0, 0}};
		dys[2] = new int[][]{{0, 0}, {-1, 1}};

		dxs[3] = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
		dys[3] = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

		dxs[4] = new int[][]{{0, -1, 0}, {-1, 0, 1}, {0, 1, 0}, {1, 0, -1}};
		dys[4] = new int[][]{{-1, 0, 1}, {0, 1, 0}, {1, 0, -1}, {0, -1, 0}};

		dxs[5] = new int[][]{{-1, 0, 1, 0}};
		dys[5] = new int[][]{{0, -1, 0, 1}};

		map = new int[N][M];
		cctvs = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (1 <= map[i][j] && map[i][j] <= 5) {
					cctvs.add(new Node(i, j, 0, map[i][j]));
				}
			}
		}
		
		targetDepth = cctvs.size();
		minCount = Integer.MAX_VALUE;
		dfs(0);
		System.out.println(minCount);
	}

	private static void dfs(int index) {
		if (index == targetDepth) {
			minCount = Math.min(minCount, findZero());
			return;
		}
		Node cctv = cctvs.get(index);
		int number = cctv.number;
		for (int i = 0; i < dxs[number].length; i++) {
			paint(cctv, number, i);
			dfs(index+1);
			recoverPaint(cctv, number, i);
		}
	}

	private static void paint(Node cctv, int number, int i) {
			for (int j = 0; j < dxs[number][i].length; j++) {
				int nx = cctv.x;
				int ny = cctv.y;
				while (true) {
					nx += dxs[number][i][j];
					ny += dys[number][i][j];
					if (0<= nx && nx < N && 0 <= ny && ny < M) {
						if (map[nx][ny] <= 0) {
							map[nx][ny] -= 1;
						} else if (map[nx][ny] < 6) {
							continue;
						} else {
							break;
						}
					} else {
						break;
					}
				}
			}
		}
	private static void recoverPaint(Node cctv, int number, int i) {
			for (int j = 0; j < dxs[number][i].length; j++) {
				int nx = cctv.x;
				int ny = cctv.y;
				while (true) {
					nx += dxs[number][i][j];
					ny += dys[number][i][j];
					if (0<= nx && nx < N && 0 <= ny && ny < M) {
						if (map[nx][ny] < 0) {
							map[nx][ny] += 1;
						} else if (map[nx][ny] < 6) {
							continue;
						} else {
							break;
						}
					} else {
						break;
					}
				}
			}
		}

	private static int findZero() {
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0)  {
					count++;
				}
			}
		}
		return count;
	}

}
