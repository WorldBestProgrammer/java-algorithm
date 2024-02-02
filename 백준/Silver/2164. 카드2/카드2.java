import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	public static void main(String[] args) throws IOException {
		// 전형적인 deque 문제
		// 개수가 하나 남을 때까지 위를 하나 버리고 위를 아래로 옮긴다
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		if (N == 1) {
			sb.append(N);
			System.out.println(sb);
			return;
		}
		
		Deque<Integer> deque = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			deque.offer(i);
		}
		while (true) {
			deque.poll();
			if (deque.size() == 1) {
				sb.append(deque.poll());
				break;
			}
			deque.addLast(deque.poll());
		}
		System.out.println(sb);
	}
}
