import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int time;
	static class Node {
		int x;
		int time;
		int brother;
		public Node(int x, int time, int brother) {
			super();
			this.x = x;
			this.time = time;
			this.brother = brother;
		}
	}
	static StringBuilder sb = new StringBuilder();
	static boolean[] visited;
	private static boolean[] even;
	private static boolean[] odd;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		if (N==K) {
			System.out.println(0);
			return;
		}
		even = new boolean[500001];
		odd = new boolean[500001];
		bfs(new Node(N, 0, K));
//		for (int i = 0; i <= 52; i++) {
//			System.out.println(i);
//			System.out.println("even " + even[i]);
//			System.out.println("odd " + odd[i]);
//		}
		System.out.println(sb);
	}

	// 홀수 시간인지 짝수 시간인지 체킹
	// 만약 동생이 도착했는 곳이 홀수 시간 체킹이 된 곳이고 홀수 시간에 도착했다면 그 시간 리턴
	// 반대로 동생이 도착한 곳이 짝수 시간 체킹이 된 곳이고 짝수 시간에 도착했다면 그 시간 리턴
	private static void bfs(Node node) {
		Deque<Node> deque = new ArrayDeque<>();
		deque.add(node);
		int[] dx = {-1, 1, 2};
		even[node.x] = true;
		while(!deque.isEmpty()) {
			node = deque.poll();
			int x = node.x;
			int time = node.time;
			int brother = node.brother;
			if (brother > 500000) {
				sb.append(-1);
				return;
			}
			if (time % 2 == 0 && even[brother] == true) {
				sb.append(time);
				return;
			} else if (time % 2 == 1 && odd[brother] == true) {
				sb.append(time);
				return;
			}
			for (int i = 0; i < 3; i++) {
				int nx = x + dx[i];
				if (i == 2) {
					nx = x * 2;
				}
				if (0 <= nx && nx <= 500000) {
//					System.out.println("nx " + nx + " time " + (time + 1));
			
					if ((time + 1) % 2 == 0) {
						if (even[nx]) {
							continue;
						}
						even[nx] = true;
					} else {
						if (odd[nx]) {
							continue;
						}
						odd[nx] = true;
					}
					deque.add(new Node(nx, time + 1, brother + time + 1));
				
				}
			}
			}
		}
		
}
