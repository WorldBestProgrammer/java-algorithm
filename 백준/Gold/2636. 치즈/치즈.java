import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static boolean[][] visited;
	static int N;
	static int M;
	static int[] point;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		map[0][0] = -1;
		
		int cheeseNumber = 0;
		int count = 0;
		int temp = 0;
		point = new int[] {0, 0};
		while ((cheeseNumber = findCheeseNumber()) > 0) {
			temp = cheeseNumber;
			visited = new boolean[N][M];
			bfs(point);
			count++;
		}
		sb.append(count).append("\n").append(temp);
		System.out.println(sb);
	}

	private static int findCheeseNumber() {
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) {
					count++;
				}
			}
		}
		return count;
	}

	private static void bfs(int[] start) {
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, -1, 0, 1};
		
		Deque<int[]> deque = new ArrayDeque<>();
		deque.add(start);
		while(!deque.isEmpty()) {
			int[] point = deque.poll();
			int x = point[0];
			int y = point[1];
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (0<= nx && nx < N && 0<= ny && ny < M && !visited[nx][ny]) {
					if (map[nx][ny] == 0) {
						deque.add(new int[] {nx, ny});
					} else if (map[nx][ny] == 1) {
						map[nx][ny] = 0;
						}
					visited[nx][ny] = true;
					} 
				}
			}
		}
	
	
	private static void meltCheese() {
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, -1, 0, 1};
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int x = i;
				int y = j;
				if (map[x][y] != -1) {
					continue;
				}
				
				for (int k = 0; k < 4; k++) {
					int nx = x + dx[k];
					int ny = y + dy[k];
					
					if (0<= nx && nx < N && 0<= ny && ny < M && map[nx][ny] != -1) {
						map[nx][ny] = 0;
					}
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == -1) {
					map[i][j] = 0;
				}
			}
		}
		
	}
	
}
