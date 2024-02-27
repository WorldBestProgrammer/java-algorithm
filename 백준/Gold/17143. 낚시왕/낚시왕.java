import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 이차원 좌표맵에서 시간마다 움직이는 상어의 움직임과 상호작용(잡아먹음)을 시뮬레이션으로 만들어야 하는 문제이다.
 * 낚시꾼은 시간마다 오른쪽으로 움직이며 그 열의 제일 위의 행의 상어 한마리를 잡는다.
 * 순서는 다음과 같다.
 * 1. 낚시꾼이 시간마다 오른쪽으로 움직인다.
 * 2. 상어를 잡는다.
 * 3. 상어가 움직인다.
 * 
 * 이에 따라 풀이 또한 위의 세 단계로 나누어 주었다.
 * 제일 먼저 낚시꾼을 움직이고 상어를 잡는다. 여기까지는 간단하다.
 * 
 * 문제는 상어의 움직임을 구현해야 하는 것이다.
 * 모든 상어가 움직여야 하기 때문에 상어의 위치를 따로 자료구조로 저장한다.
 * 그 후 각 위치에 있는 상어들의 좌표를 속도와 방향에 따라 갱신해준다.
 * 이미 그 좌표에 상어가 있다면 크기를 비교하여 더 큰 상어만 남겨준다.
 * 
 * 주의할 점은 상어의 속도는 1부터 1000까지라는 것이다.
 * 최대 10,000마리의 상어에 대해 1000까지를 일일이 시뮬레이션을 돌리며 모두 움직여주게 되면 10,000,000이 나오고 여기에 낚시꾼은 최대 100까지 움직일 수 있기 때문에 시간 초과가 난다. 
 * 따라서 주기를 이용하여 상어의 움직임 시뮬레이션을 줄여준다.
 */
public class Main {

    static int R; // 행
    static int C; // 열
    static int totalSize; // 잡은 상어 크기의 합
    static int[][][] map;
    static int nr; // 현재 행
    static int nc; // 현재 열
    static int direction; // 현재 방향

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        map = new int[R + 1][C + 1][];
        /**
         * 상어의 위치는 겹칠 수 있기 때문에 set으로 관리한다.
         */
        List<Point> sharks = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int[] shark = new int[5];
            for (int j = 0; j < 5; j++) {
                shark[j] = Integer.parseInt(st.nextToken());
            }
            Point point = new Point(shark[0], shark[1]);
            map[shark[0]][shark[1]] = new int[]{shark[2], shark[3], shark[4]};
            sharks.add(point);
        }

        int fishingKingLoc = 0;
        while (fishingKingLoc != C) {
            // 낚시왕 이동
            fishingKingLoc++;
            // 상어 잡기
            int size = -1;
            for (int i = 1; i <= R; i++) {
                if (map[i][fishingKingLoc] != null) {
                    // 상어를 잡았다면
                    size = map[i][fishingKingLoc][2];
                    totalSize += size;
                    map[i][fishingKingLoc] = null;
                    break;
                }
            }
            // 상어 이동
            int[] dr = {0, -1, 1, 0, 0};
            int[] dc = {0, 0, 0, 1, -1};

            int[][][] newMap = new int[R + 1][C + 1][];
            List<Point> newSharks = new ArrayList<>();

            for (Point loc : sharks) {
                int r = loc.x;
                int c = loc.y;
                int[] shark = map[r][c];
                if (shark == null) {
                	continue;
                }
                int speed = shark[0];
                direction = shark[1];
                int sharkSize = shark[2];
                nr = r;
                nc = c;
                int time = 0;
                
                /**
                 * 한칸을 미리 움직여 주는 이유는 모든 경우에 대해 주기를 이용하기 위해서이다.
                 * 다시 말해 한칸을 미리 움직여 주지 않으면 특정 경우에 대해서는 주기를 이용할 수 없다.
                 * 예를 들어, 상어가 제일 윗칸에 있는데 아래를 바라보고 있다고 하자.
                 * 이런 상황에서는 상어가 다시 제자리에 돌아왔을 때는 무조건 위를 바라보게 된다.
                 * 즉, 방향이 달라지게 되어 별도의 예외처리가 필요해진다.
                 * 하지만 한 칸을 움직여주면 이러한 예외상황을 모두 전처리할 수 있기 때문에 상황이 간단해진다.
                 */
                int newSpeed = 0;
                if (direction >= 3) { // 좌우 방향이라면
                    newSpeed = speed % ((C - 1) * 2); // (C-1) * 2가 하나의 주기가 된다.
                } else { // 위아래 방향이라면
                    newSpeed = speed % ((R - 1) * 2); // 마찬가지로 (R-1) * 2가 하나의 주기가 된다.
                }
                /**
                 * 주기를 모두 고려하고 남은 나머지에 대해서는 직접 움직임 시뮬레이션을 돌려준다.
                 */
                while (time < newSpeed) {
                    time++;
                    movingOneStep(dr, dc);
                }
                
                if (newMap[nr][nc] != null) { // 이미 그 자리에 상어가 있다면 크기에 따라 갱신한다.
                    int[] sameLocShark = newMap[nr][nc];
                    if (sameLocShark[2] < sharkSize) {
                    	newMap[nr][nc] = new int[]{speed, direction, sharkSize};
                    }
                } else { // 이미 그 자리에 상어가 없다면
                	newMap[nr][nc] = new int[]{speed, direction, sharkSize};
                    newSharks.add(new Point(nr, nc));
                }
                
              }
            map = newMap;
            sharks = newSharks;
        }

        System.out.println(totalSize);
    }

    /**
     * 방향에 따라 한칸을 움직여준다.
     * 만약 더 이상 갈 수 없다면 방향을 바꾸고 한칸을 움직여준다.
     * @param dr : int[], 행 움직임 배열
     * @param dc : int[], 열 움직임 배열
     */
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

    /**
     * 디버깅용 이차원맵 출력
     */
    private static void printMap() {
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (map[i][j] == null) {
                    System.out.print("0");
                } else {
//                    System.out.print(" " + map[i][j].get(0)[2]);
                    System.out.print("1");
                }
            }
            System.out.println();
        }
    }
}

