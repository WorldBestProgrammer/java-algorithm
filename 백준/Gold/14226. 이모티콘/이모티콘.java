import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    private static class ClipBoard {

        private int stored = 0;

        public void copy(int number) {
            stored += number;
        }

        public int paste() {
            int temp = stored;
            stored = 0;
            return temp;
        }
    }

    private static class Display {
        private static int emoticon = 0;

        ClipBoard clipBoard = new ClipBoard();

        public void copy() {
            clipBoard.copy(emoticon);
        }

        public void paste() {
            emoticon += clipBoard.paste();
        }

        public void delete() {
            if (emoticon > 0) {
                emoticon--;
            }
        }

        public boolean isSatisfied(int S) {
            return emoticon == S;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int S = scanner.nextInt();

        int minTime = bfs(S);

        System.out.println(minTime);


    }

    private static int bfs(int S) {

        boolean[][] visited = new boolean[1001][1001];

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 1, 0));

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (node.total == S) {
                return node.time;
            }
            
            if (!visited[node.total][node.total]) {
                queue.offer(new Node(node.total, node.total, node.time + 1));
                visited[node.total][node.total] = true;
            }

            if (node.clipBoard != 0 && node.total + node.clipBoard <= S && !visited[node.clipBoard][
                    node.total + node.clipBoard]) {
                queue.offer(new Node(node.clipBoard, node.total + node.clipBoard, node.time + 1));
                visited[node.clipBoard][node.total + node.clipBoard] = true;
            }

            if (node.total >= 1 && !visited[node.clipBoard][node.total - 1]) {
                queue.offer(new Node(node.clipBoard, node.total - 1, node.time + 1));
                visited[node.clipBoard][node.total - 1] = true;
            }
        }
        return -1;
    }

    private static class Node {
        int clipBoard;
        int total;
        int time;

        public Node(int clipBoard, int total, int time) {
            this.clipBoard = clipBoard;
            this.total = total;
            this.time = time;
        }
    }
}
