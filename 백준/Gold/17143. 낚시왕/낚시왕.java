import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static int R;
    static int C;
    static int totalSize;
    static List<int[]>[][] map;
    static int nr;
    static int nc;
    static int direction;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        map = new List[R+1][C+1];
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        Set<int[]> sharks = new HashSet<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int[] shark = new int[5];
            for (int j = 0; j < 5; j++) {
                shark[j] = Integer.parseInt(st.nextToken());
            }
            map[shark[0]][shark[1]].add(new int[]{shark[2], shark[3], shark[4]});
            sharks.add(shark);
        }


        int fishingKingLoc = 0;
        while (fishingKingLoc != C) {
            // 낚시왕 이동
            fishingKingLoc++;
//            System.out.println("----------------------");
//            System.out.println(fishingKingLoc);
//            System.out.println("초기");
//            printMap();
            // 상어 잡기
            int size = -1;
            for (int i = 1; i <= R; i++) {
                if (!map[i][fishingKingLoc].isEmpty()) {
                    // 상어를 잡았다면
                    size = map[i][fishingKingLoc].get(0)[2];
                    totalSize += size;
                    map[i][fishingKingLoc].remove(0);
                    sharks.remove(new int[] {i, fishingKingLoc});
                    break;
                }
            }
//            System.out.println("상어 잡은 후");
//            printMap();
            // 상어 이동
            int[] dr = {0, -1, 1, 0, 0};
            int[] dc = {0, 0, 0, 1, -1};

            List<int[]>[][] newMap = new List[R+1][C+1];
            for (int i = 1; i <= R; i++) {
                for (int j = 1; j <= C; j++) {
                    newMap[i][j] = new ArrayList<>();
                }
            }
            Set<int[]> newSharks = new HashSet<>();

            for (int[] loc : sharks) {
                int r = loc[0];
                int c = loc[1];
                for (int[] shark : map[r][c]) {
                    int speed = shark[0];
                    direction = shark[1];
                    int sharkSize = shark[2];
                    nr = r;
                    nc = c;
                    int time = 0;
                    if (speed > 0) {
                        movingOneStep(dr, dc);
//                        time++;
//                        System.out.println("하나 이동");
//                        System.out.println(speed + " " + nr + " " + nc);
                        int newSpeed = 0;
                        if (direction >= 3) {
                            newSpeed = (speed - 1) % ((C - 1) * 2);
                        } else {
                            newSpeed = (speed - 1) % ((R - 1) * 2);
                        }
                        while (time < newSpeed) {
                            time++;
                            movingOneStep(dr, dc);
                        }
                    }
                    if (!newMap[nr][nc].isEmpty()) {
                        int[] sameLocShark = newMap[nr][nc].get(0);
                        if (sameLocShark[2] < sharkSize) {
                            newMap[nr][nc].set(0, new int[]{speed, direction, sharkSize});
                        }
                    } else {
                        newMap[nr][nc].add(new int[]{speed, direction, sharkSize});
                        newSharks.add(new int[]{nr, nc});
                    }
                }
            }
            map = newMap;
            sharks = newSharks;
//            System.out.println("상어 이동 후");
//            printMap();
        }

        System.out.println(totalSize);
    }

    private static void movingOneStep(int[] dr, int[] dc) {
        int tempNr = nr + dr[direction];
        int tempNc = nc + dc[direction];

        if (!(1 <= tempNr && tempNr <= R && 1 <= tempNc && tempNc <= C)) {
            if (direction >= 3) {
                direction = Math.abs(4 - direction) + 3;
            } else {
                direction = Math.abs(2 - direction) + 1;
            }
            tempNr = nr + dr[direction];
            tempNc = nc + dc[direction];
        }

        nr = tempNr;
        nc = tempNc;
    }

    private static void printMap() {
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (map[i][j].isEmpty()) {
                    System.out.print("0");
                } else {
                    System.out.print(" " + map[i][j].get(0)[2]);
//                    System.out.print("1");
                }
            }
            System.out.println();
        }
    }
}
