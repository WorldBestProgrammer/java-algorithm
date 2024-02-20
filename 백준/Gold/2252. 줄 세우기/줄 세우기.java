import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static boolean[] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<Integer>[] indegree = new List[N+1];
		List<Integer>[] outdegree = new List[N+1];
		for (int i = 1; i <= N; i++) {
			indegree[i] = new ArrayList<>();
			outdegree[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			indegree[b].add(a);
			outdegree[a].add(b);
		}
		
		visited = new boolean[N+1];
		Deque<Integer> deque = new ArrayDeque<>();
		out: while (deque.size() <= N) {
			for (int i = 1; i <= N; i++) {
				if (visited[i] == true) {
					continue;
				}
				if (indegree[i].size() == 0) {
					deque.add(i);
					visited[i] = true;
					if (deque.size() == N) {
						break out;
					}
					
					for (int out : outdegree[i]) {
						indegree[out].remove(Integer.valueOf(i));
					}
				}
			}
		}
		while(!deque.isEmpty()) {
			sb.append(deque.removeFirst()).append(" ");
		}
		System.out.println(sb);
	}
}
