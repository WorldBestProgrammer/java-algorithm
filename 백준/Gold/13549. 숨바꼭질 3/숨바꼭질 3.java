import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static boolean[] visited;
    static int N;
    static int K;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visited = new boolean[100001];
        bfs(new int[] {N, 0});
        System.out.println(sb);
    }
//    (cur[0] !=0 && K % cur[0] == 0)
    private static void bfs(int[] start) {
        int[] dx = {-1, 1};
        Deque<int[]> deque = new ArrayDeque<>();
        deque.offer(start);
        visited[start[0]] = true;
        while (!deque.isEmpty()) {
            int[] cur = deque.poll();
            if (cur[0] == K ) {
                sb.append(cur[1]);
                return;
            }
            int next = cur[0];
            while (next != 0 && next * 2 <= 100000) {
                next *= 2;
                if (!visited[next]) {
                    deque.offer(new int[]{next, cur[1]});
                    visited[next] = true;
                }
            }
            for (int i = 0; i < 2; i++) {
                next = cur[0] + dx[i];
                if (0 <= next && next <= 100000 && !visited[next]) {
                    deque.offer(new int[] {next, cur[1] + 1});
                    visited[next] = true;
                }
            }

        }
    }
}