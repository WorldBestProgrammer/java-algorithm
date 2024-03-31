import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int m;
    static int[] prev;
    static List<Integer>[] graph;
    static int[][] capacities;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        capacities = new int[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);

            capacities[a][b] = c;
            capacities[b][a] = c;
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        edmond_karp(s, t);

        int maxFlow = 0;
        for (int nv : graph[s]) {
            maxFlow += (capacities[s][nv] + capacities[nv][s]) / 2 - capacities[s][nv];
        }
        System.out.println(maxFlow);
    }

    private static void edmond_karp(int s, int t) {
        while (true) {
            prev = new int[n + 1];
            if (!bfs(s, t)) {
                break;
            }

            int minWeight = Integer.MAX_VALUE;
            int cur = t;
            do {
                minWeight = Math.min(minWeight, capacities[prev[cur]][cur]);
                cur = prev[cur];
            } while (cur != s);

            cur = t;
            do {
                capacities[prev[cur]][cur] -= minWeight;
                capacities[cur][prev[cur]] += minWeight;
                cur = prev[cur];
            } while (cur != s);
        }
    }

    private static boolean bfs(int s, int t) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];
        queue.add(s);
        visited[s] = true;
        while (!queue.isEmpty()) {
            int v = queue.poll();
            if (v == t) {
                return true;
            }
            for (int nv : graph[v]) {
                if (!visited[nv] && capacities[v][nv] > 0) {
                    queue.add(nv);
                    visited[nv] = true;
                    prev[nv] = v;
                }
            }
        }
        return false;
    }
}
