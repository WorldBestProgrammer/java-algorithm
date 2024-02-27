import java.util.Scanner;

/**
 * 1초 : 낚시왕 오른쪽 한 칸 이동 -> 낚시왕이 서있는 열에서 땅과 가장 가까운 상어 잡기 -> 잡은 상어사라지고 이동
 * 상어이동 후 한 칸에 상어가 두 마리 이상있는 경우 가장 큰 상어가 모두 잡아 먹는다.
 * 낚시왕이 잡은 상어 크기의 합 구하기
 * 입력 : 격자판의 크기 R, C와 상어의 수 M (2 ≤ R, C ≤ 100, 0 ≤ M ≤ R×C)
 *       M개의 줄에 상어의 정보 
 *       r, c, s, d, z (1 ≤ r ≤ R, 1 ≤ c ≤ C, 0 ≤ s ≤ 1000, 1 ≤ d ≤ 4, 1 ≤ z ≤ 10000)
 *       (r, c) 상어의 위치, s 속력, d 이동 방향,z 크기
 *       d 방향 :  1 위, 2 아래, 3 오른쪽, 4 왼쪽을 의미
 * @author SSAFY
 *
 */
public class Main{
	static int r; //상어위치 행
	static int c; //상어위치 열
	static int m; //상어 수
	static Shark[][] board;
	static Shark[][] temp;    // 상어 이동을 저장할 배열
	static int[] mv_y = {-1,1,0,0};
	static int[] mv_x = {0,0,1,-1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		r = sc.nextInt();
		c = sc.nextInt();
		m = sc.nextInt();
		board = new Shark[r][c];
        // 입력 부분
		for(int i = 0 ; i < m ; i++) {
			int y = sc.nextInt(); //상어 위치 행
			int x = sc.nextInt(); //상어 위치 열
			int s = sc.nextInt(); //상어 속도
			int d = sc.nextInt(); //방향
			int z = sc.nextInt(); //크기
			board[y-1][x-1] = new Shark(s,d-1,z);
		}
		
		int fisherMan = -1; // 어부의 초기위치
		int ans = 0;
		while(true) {
			fisherMan++;                // 어부 오른쪽으로 이동
			if(fisherMan >= c) break;   // 어부가 맵을 벗어나면 종료
			for(int i = 0 ; i < r ; i++) {  // 어부가 물고기를 잡는다.
				if(board[i][fisherMan] != null) {
					ans += board[i][fisherMan].getSize();
					board[i][fisherMan] = null;
					break;
				}
			}
			temp = new Shark[r][c]; // 어부가 물고기를 잡은 뒤 물고기들이 이동한다.
			for(int y = 0 ; y < r ; y++) {
				for(int x = 0 ; x < c ; x++) {
					if(board[y][x] != null) {
						int ny = y;
						int nx = x;
						int temp_s = board[y][x].getSpeed();
						int temp_d = board[y][x].getDir();
						int temp_z = board[y][x].getSize();
						int dirY = mv_y[temp_d];
						int dirX = mv_x[temp_d];
                        // 물고기는 특정 열이나 행을 왕복운동 하니까 물고기가 실제로 움직이는 거리는 전체 움직인 거리 - 제자리롤 돌아오는 거리이다.
						if(temp_d == 2 || temp_d == 3) temp_s %= (c-1)*2;  
						else temp_s %= (r-1)*2;
						for(int i = 0 ; i<temp_s ; i++) { // 물고기 이동
							if(ny +dirY < 0 || ny + dirY >= r || nx + dirX < 0 || nx + dirX >= c) {
								dirY = -dirY;
								dirX = -dirX;
								if (temp_d == 0) {
									temp_d = 1;
								}else if(temp_d == 1) {
									temp_d = 0;
								}else if(temp_d == 2) {
									temp_d = 3;
								}else if(temp_d == 3) {
									temp_d = 2;
								}
							}
							ny += dirY;
							nx += dirX;
						}
						if(temp[ny][nx] == null) temp[ny][nx] = new Shark(temp_s,temp_d,temp_z); // 움직인 곳에 물고기 없으면 바로 들어감
						else if(temp[ny][nx] != null && temp[ny][nx].getSize() < temp_z){       // 움직인 곳에 물고기 있으면 큰것만 남겨놓음
							temp[ny][nx] = new Shark(temp_s,temp_d,temp_z);
						}
					}
				}
			}
			for(int y = 0 ; y < r ; y++) {
				for(int x = 0 ; x < c ; x++) {
					board[y][x] = temp[y][x];  //보드 갱신
				}
			}
		}
		System.out.print(ans);
	}
}

/**
 *  상어정보
 *  speed : s 속력  
 *  dir   : d 이동 방향
 *  size  : z 크기
 * 
 */
class Shark{
	int speed;
	int dir;
	int size;
	public Shark(int speed, int dir, int size) {
		super();
		this.speed = speed;
		this.dir = dir;
		this.size = size;
	}
	public int getSpeed() {
		return speed;
	}
	public int getDir() {
		return dir;
	}
	public int getSize() {
		return size;
	}
}
