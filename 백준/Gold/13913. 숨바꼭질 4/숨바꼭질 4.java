import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int K;
    static int minDepth;
    static boolean[] visited;
    static boolean flag;
    static List<Integer> result;
    static StringBuilder sb = new StringBuilder();
    static class Node {
        int position;
        List<Integer> path;
        int depth;

        public Node(int position) {
            this.position = position;
            path = new LinkedList<>();
        }

        public Node(int position, int depth) {
            this.position = position;
            this.depth = depth;
        }

        public Node(int position, List<Integer> path) {
            this.position = position;
            this.path = path;
        }


    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        List<Integer> integers = null;
        if (N < K) {
            visited = new boolean[100001];
            integers = bfs(new Node(N));
        } else {
            sb.append(N - K).append("\n");
            for (int i = N; i >= K; i--) {
                sb.append(i).append(" ");
            }
            System.out.println(sb);
            return;
        }

//        System.out.println(minDepth);
//        visited = new boolean[100001];
//        dfs(new Node(N));
        sb.append(integers.size()).append("\n");
        sb.append(N).append(" ");
        for (Integer integer : integers) {
            sb.append(integer).append(" ");
        }
        System.out.println(sb);
    }

    private static List<Integer> bfs(Node node) {
        Deque<Node> deque = new ArrayDeque<>();
        deque.add(node);
        int[] dx = {-1, 1, 2};
        while (!deque.isEmpty()) {
            Node curNode = deque.poll();
            int x = curNode.position;
            List<Integer> path = curNode.path;
            if (x == K) {
                return path;
//                return path;
            }
            for (int i = 0; i < 3; i++) {
                int nx = x + dx[i];
                if (i == 2) {
                    nx = x * 2;
                }
                if (0 <= nx && nx <= 100000 && !visited[nx]) {
                    List<Integer> curPath = new ArrayList<>(path);
//                    Collections.copy(curPath, path);
                    curPath.add(nx);
                    visited[nx] = true;
                    deque.add(new Node(nx, curPath));
                }
            }
        }
        return null;
    }

    private static void dfs(Node node) {
        int[] dx = {-1, 1, 2};
        int x = node.position;
        List<Integer> path = node.path;
//        System.out.println(x + " " + path.size());
        if (x == K && path.size() == minDepth) {
            result = path;
            flag = true;
            return;
        }
        for (int i = 0; i < 3; i++) {
            int nx = x + dx[i];
            if (i == 2) {
                nx = x * 2;
            }
            if (0 <= nx && nx <= 100000 && !visited[nx]) {
                path.add(nx);
                visited[nx] = true;
                node.position = nx;
                dfs(node);
                if (flag) {
                    return;
                }
                node.position = x;
                visited[nx] = false;
                path.remove(path.size() - 1);
            }
        }

    }
}
