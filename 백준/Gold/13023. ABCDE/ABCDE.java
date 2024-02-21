import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static boolean[] visited;
	static List<Integer>[] graph;
	static int N;
	static int M;
	static boolean flag;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new List[N];
		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph[a].add(b);
			graph[b].add(a);
		}
		
		for (int i = 0; i < N; i++) {
			visited = new boolean[N];
			visited[i] = true;
			dfs(i, 1);
			if (flag) {
				System.out.println(1);
				return;
			}
		}
		System.out.println(0);
	}

	private static void dfs(int position, int depth) {
		if(depth == 5) {
			flag = true;
			return;
		}
		
		for (int nextPosition : graph[position]) {
			if (!visited[nextPosition]) {
				visited[nextPosition] = true;
				dfs(nextPosition, depth + 1);
				if (flag) {
					return;
				}
				visited[nextPosition] = false;
			}
		}
	}
}
