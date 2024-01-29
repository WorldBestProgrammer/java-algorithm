import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	static int[][] map = new int[19][19];
	static boolean[][] v_cross1 = new boolean[19][19];
	static boolean[][] v_cross2 = new boolean[19][19];
	static boolean[][] v_row = new boolean[19][19];
	static boolean[][] v_col = new boolean[19][19];
	
	// code 0: 이기고 비긴경우 없음 / code 1: 이김 / code 2: 비김
	static boolean checkCross1(int x, int y) {
		v_cross1[x][y] = true;
		int i = 0, cnt = 0;
		while(x + i < 19 && y + i < 19) {
			if(map[x + i][y + i] != map[x][y])
				break;
			cnt += 1;
			v_cross1[x + i][y + i] = true;
			i++;
		}
		
		if(cnt == 5)
			return true;
		return false;
		
	}
	
	
	static boolean checkCross2(int x, int y) {
		v_cross2[x][y] = true;
		int i = 0, cnt = 0;
		while(x - i >= 0 && y + i < 19) {
			if(map[x - i][y + i] != map[x][y])
				break;
			cnt += 1;
			v_cross2[x - i][y + i] = true;
			i++;
		}
		
		if(cnt == 5)
			return true;
		
		return false;
	}
	
	static boolean checkRow(int x, int y) {
		v_row[x][y] = true;
		int i = 0, cnt = 0;
		while(x + i < 19) {
			if(map[x + i][y] != map[x][y])
				break;
			cnt += 1;
			v_row[x + i][y] = true;
			i++;
		}
		
		if(cnt == 5)
			return true;
		return false;
		
	}
	
	static boolean checkCol(int x, int y) {
		v_col[x][y] = true;
		int i = 0, cnt = 0;
		while(y + i < 19) {
			if(map[x][y + i] != map[x][y])
				break;
			cnt += 1;
			v_col[x][y + i] = true;
			i++;
		}
		
		if(cnt == 5)
			return true;
		return false;
		
	}
	
	

	public static void main(String[] args) throws Exception {
		//여기에 코드를 작성하세요.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[]winner = null;
		int ans = 0;
		
		for(int i = 0; i < 19; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 19; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
			}
		}
		for(int i = 0; i < 19; i++) {
			for(int j = 0; j < 19; j++) {
				if(map[i][j] == 0)
					continue;
				if(!v_cross1[i][j]) {
					if(checkCross1(i, j)) {
						winner = new int[] {i + 1, j + 1};
						ans = map[i][j];
						
					}
				}
				
				if(!v_row[i][j]) {
					if(checkRow(i, j)) {
						winner = new int[] {i + 1, j + 1};
						ans = map[i][j];
					}
				}
				if(!v_col[i][j]) {
					if(checkCol(i, j)) {
						winner = new int[] {i + 1, j + 1};
						ans = map[i][j];
					}
				}
				
			}
		}
		
		for(int i = 18; i > 0; i--) {
			for(int j = 0; j < 19; j++) {
				if(map[i][j] == 0)
					continue;
				if(!v_cross2[i][j]) {
					if(checkCross2(i, j)) {
						winner = new int[] {i + 1, j + 1};
						ans = map[i][j];
					}
				}
			}
		}
		
		
		if(winner == null)
			System.out.println(0);
		else {
			System.out.println(ans);
			System.out.println(winner[0] + " " + winner[1]);
		}
		
		
		
		
		
	}
}

