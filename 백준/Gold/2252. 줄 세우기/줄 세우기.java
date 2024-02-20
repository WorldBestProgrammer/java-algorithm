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
	static int count;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] indegree = new int[N+1];
		List<Integer>[] outdegree = new List[N+1];
		for (int i = 1; i <= N; i++) {
			outdegree[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			indegree[b]++;
			outdegree[a].add(b);
		}
		count = 0;
		visited = new boolean[N+1];
		Deque<Integer> deque = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) {
				deque.add(i);
			}
		}
		
		while(!deque.isEmpty()) {
			
			int cur = deque.poll();
			sb.append(cur).append(" ");
			for (int out : outdegree[cur]) {
				indegree[out]--;
				if(indegree[out] == 0) {
					deque.offer(out);
				}
			}
		}
		
//		out: while (true) {
//			for (int i = 1; i <= N; i++) {
//				if (visited[i] == true) {
//					continue;
//				}
//				if (indegree[i] == 0) {
//					sb.append(i).append(" ");
//					visited[i] = true;
//					count++;
//					if (count == N) {
//						break out; 
//					}
//					
//					for (int out : outdegree[i]) {
//						indegree[out]--;
//					}
//				}
//			}
//		}
		System.out.println(sb);
	}
}