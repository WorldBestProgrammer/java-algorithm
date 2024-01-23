import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    private static class Node {
        private final int time;
        private final int position;

        public Node(int time, int position) {
            this.time = time;
            this.position = position;
        }

        public int getTime() {
            return time;
        }

        public int getPosition() {
            return position;
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int K = scanner.nextInt();

        if (N==K) {
            System.out.println(0);
            return;
        }
        Node node = new Node(1, N);
        // 최단 거리이기 때문에 BFS로 구현 가능하다.
        int LeastTime = bfs(node, K);
        System.out.println(LeastTime);
    }

    private static int bfs(Node node, int k) {
        Queue<Node> queue = new LinkedList<>();

        int[] dx = {-1, 1, 2};
        boolean[] visited = new boolean[100001];
        queue.offer(node);
        visited[node.getPosition()] = true;
        while (!queue.isEmpty()) {
            node = queue.poll();
            for (int j : dx) {
                int nextPosition;
                if (j == 2) {
                    nextPosition = 2 * node.getPosition();
                } else {
                    nextPosition = node.getPosition() + j;
                }

                if (nextPosition == k) {
                    return node.getTime();
                }

                if (nextPosition >= 0 && nextPosition < 100_001 && !visited[nextPosition]) {
                    queue.offer(new Node(node.getTime() + 1, nextPosition));
                    visited[nextPosition] = true;
                }
            }

        }

        return 0;
    }
}
