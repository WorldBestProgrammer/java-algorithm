import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	
	static SortedSet<Integer>[] graph;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		
		graph = new SortedSet[N+1];
		for (int i = 1; i <=N; i++) {
			graph[i] = new TreeSet<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		
		
		visited = new boolean[N+1];
		visited[V] = true;
		dfs(V);
		sb.append("\n");
		visited = new boolean[N+1];
		visited[V] = true;
		bfs(V);
		sb.append("\n");
		
		System.out.println(sb);
	}
	private static void dfs(int node) {
		
		sb.append(node).append(" ");
		for (int nextNode : graph[node]) {
			if (visited[nextNode] != true) {
				visited[nextNode] = true;
				dfs(nextNode);
			}
		}
	}
	private static void bfs(int node) {
		
		Deque<Integer> deque = new ArrayDeque<>();
		deque.offer(node);
		while (!deque.isEmpty()) {
			node = deque.poll();
			sb.append(node).append(" ");
			for (int nextNode : graph[node]) {
				if (visited[nextNode] != true) {
					visited[nextNode] = true;
					deque.add(nextNode);
				}
			}
		}
	}
}
