import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};
    static int[][] map;
    static Deque<Horse>[][] horse;
    static int N;
    static int K;
    static int[][] loc;
    static int cnt;
    static Deque<Horse> temp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        loc = new int[K + 1][2];

        map = new int[N + 1][N + 1];
        horse = new Deque[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                horse[i][j] = new ArrayDeque<>();
            }
        }
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            horse[r][c].add(new Horse(i, dir-1));
            loc[i] = new int[]{r, c};
        }

        cnt = 1;
        while (cnt <= 1000) {
            if (playGame()) {
                System.out.println(cnt);
                return;
            }
            cnt++;
        }

        System.out.println(-1);
    }

    private static boolean playGame() {
        for (int i = 1; i <= K; i++) {
            int r = loc[i][0];
            int c = loc[i][1];

            temp = new ArrayDeque<>();

            Deque<Horse> horses = horse[r][c];
            while (true) {
                Horse horse = horses.pollFirst();
                temp.add(horse);
                if (horse.id == i) {
                    break;
                }
            }
            Horse cur = temp.getLast();
            int nr = r + dr[cur.dir];
            int nc = c + dc[cur.dir];

            // 파란색 or 넘을때
            if (!(1 <= nr && nr <= N && 1 <= nc && nc <= N) || map[nr][nc] == 2) {
                int newDir;
                if (cur.dir <= 1) {
                    newDir = 1 - cur.dir;
                } else {
                    newDir = 5 - cur.dir;
                }
                nr = r + dr[newDir];
                nc = c + dc[newDir];
                temp.getLast().dir = newDir;
            }

            if (!(1 <= nr && nr <= N && 1 <= nc && nc <= N) || map[nr][nc] == 2){
                    while (!temp.isEmpty()) {
                        Horse hor = temp.pollLast();
                        horse[r][c].addFirst(hor);
                    }
                    continue;
                }
            else if (map[nr][nc] == 0) {
                while (!temp.isEmpty()) {
                    Horse hor = temp.pollLast();
                    horse[nr][nc].addFirst(hor);
                    loc[hor.id] = new int[]{nr, nc};
                }
                if (horse[nr][nc].size() >= 4) {
                    return true;
                }
            } else {
                while (!temp.isEmpty()) {
                    Horse hor = temp.pollFirst();
                    horse[nr][nc].addFirst(hor);
                    loc[hor.id] = new int[]{nr, nc};
                }
                if (horse[nr][nc].size() >= 4) {
                    return true;
                }
            }
        }

        return false;
    }

    private static void print() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(horse[i][j]);
            }
            System.out.println();
        }
    }
    static class Horse {

        int id;
        int dir;

        public Horse(int id, int dir) {
            this.id = id;
            this.dir = dir;
        }

        @Override
        public String toString() {
            return
                    "id=" + id +
                    ", dir=" + dir
                    ;
        }
    }
}
