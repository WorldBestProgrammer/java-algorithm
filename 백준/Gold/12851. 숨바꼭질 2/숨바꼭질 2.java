import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] map;
	static boolean[] visited;
	static int count;
	static int number;
	static boolean flag;
	static StringBuilder sb = new StringBuilder();
	static class Node {
		int start;
		int depth;
		public Node(int start, int depth) {
			super();
			this.start = start;
			this.depth = depth;
		}
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		map = new int[100001];
		visited = new boolean[100001];
		
		if (N == K) {
			sb.append(0).append("\n").append(1);
			System.out.println(sb);
			return;
		}
		map[K] = 111;
		count = 0;
		number = 0;
		flag = false;
		bfs(new Node(N, 0));
		sb.append(count).append("\n").append(number);
		System.out.println(sb);
	}

	private static void bfs(Node start) {
		Deque<Node> deque = new ArrayDeque<>();
		deque.add(start);
		visited[start.start] = true;
		while (!deque.isEmpty()) {
			Node curs = deque.poll();
			int cur = curs.start;
			int depth = curs.depth;
			visited[cur] = true;
			if (map[cur] == 111) {
				if (!flag) {
					flag = true;
					count = depth;
					number++;
				} else {
					if (depth == count) {
						number++;
					} else if (depth > count) {
						return;
					}
				}
				visited[cur] = false;
			}
			if (0 <= cur - 1 && cur - 1 <= 100000 && visited[cur - 1] != true) {
				deque.add(new Node(cur - 1, depth+1));
//				if (map[cur - 1] == 111) {
//					continue;
//				}
//				visited[cur - 1] = true;
			} 
			if (0 <= cur + 1 && cur + 1 <= 100000 && visited[cur + 1] != true) {
				deque.add(new Node(cur + 1, depth+1));	
//				if (map[cur + 1] == 111) {
//					continue;
//				}
//				visited[cur + 1] = true;
			} 
			if (0 <= cur * 2 && cur * 2 <= 100000 && visited[cur * 2] != true) {
				deque.add(new Node(cur * 2, depth+1));	
//				if (map[cur * 2] == 111) {
//					continue;
//				}
//				visited[cur * 2] = true;
			}
		}
		
	}
}
