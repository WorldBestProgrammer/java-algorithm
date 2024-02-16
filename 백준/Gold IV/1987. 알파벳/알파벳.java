import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class Main {
	static int count;
	static int maxCount;
	static char[][] map;
	static int R;
	static int C;
	static HashSet<Character> set;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		set = new HashSet<>();
		set.add(map[0][0]);
		maxCount = Integer.MIN_VALUE;
		count = 1;
		dfs(new Point(0, 0));
		System.out.println(maxCount);
	}

	private static void dfs(Point point) {
		
//		maxCount = Math.max(maxCount, count);
		int N = map.length;
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		
		for (int i = 0; i < 4; i++) {
			int nx = point.x + dx[i];
			int ny = point.y + dy[i];
			
			if (!(0 <= nx && nx < R && 0 <= ny && ny < C)) {
				maxCount = Math.max(maxCount, count);
				continue;
			}
			
			char alphabet = map[nx][ny];
			if (!set.add(alphabet)) {
				maxCount = Math.max(maxCount, count);
//				continue;
			} else {
				count++;
				dfs(new Point(nx, ny));
				count--;
				set.remove(alphabet);
			}
		}
		
	}
}
